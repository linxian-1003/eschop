package com.neu.order_service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.common.utils.JsonModel;
import com.neu.member_service.domain.Member;
import com.neu.order_service.client.MemberServiceClient;
import com.neu.order_service.client.ProductServiceClient;
import com.neu.order_service.dao.BookDao;
import com.neu.order_service.dao.BookDetailDao;
import com.neu.order_service.domain.Book;
import com.neu.order_service.domain.BookDetail;
import com.neu.order_service.exception.CommonException;
import com.neu.order_service.exception.ExceptionEnum;
import com.neu.order_service.query.BookQuery;
import com.neu.order_service.service.BookService;
import com.neu.product_service.domain.Product;
import com.neu.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookDao bookDao;

    @Autowired
    BookDetailDao bookDetailDao;


    @Autowired
    MemberServiceClient memberServiceClient;

    @Autowired
    ProductServiceClient productServiceClient;

    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        return machineId+ String.format("%015d", hashCodeV);
    }

    @Override
    public int order(Book book) {
        List<BookDetail> bookDetails =  book.getBookDetailList();
        //简单校验和设置参数
        inspection(book);
        BigDecimal totalPrice = new BigDecimal(0);
        Integer totalCount = 0;
        //计算商品总价、商品总数量
        for(BookDetail bookDetail:bookDetails){
            //订单详情表内的商品总价
            bookDetail.setItemPrice(bookDetail.getProductPrice().multiply(new BigDecimal(bookDetail.getProductCount())));
            totalPrice = totalPrice.add(bookDetail.getItemPrice());
            totalCount += bookDetail.getProductCount();
        }
        book.setTotalPrice(totalPrice);
        book.setTotalCount(totalCount);
        //设置下单时间
        book.setOrderTime(new Date());
        //如果订单状态为空则设为0，即为未支付状态
        if(book.getStatus()==null){
            book.setStatus(0);
        }else if(book.getStatus()==1){
            book.setPayType(1);
            book.setPayTime(new Date());
            book.setPayNum(getOrderIdByUUId());
        }
        //调用getOrderIdByUUId方法生成订单号
        book.setBookNum(getOrderIdByUUId());
        //往数据库中插入订单
        int num = bookDao.order(book);
        //大于0则代表插入成功，这时候再插入订单详情表
        if(num>0){
            for(BookDetail bookDetail:bookDetails){
                bookDetail.setBookId(book.getId());
                //如果订单已支付，修改商品库存
                if(book.getStatus()==1){
                    Product product = productServiceClient.findById(bookDetail.getProductId());
                    Product temp = new Product();
                    temp.setId(bookDetail.getProductId());
                    //减库存
                    if(book.getStatus()-bookDetail.getProductCount()<0){
                        temp.setStock(product.getStock()-1);
                        //修改数据库的库存信息
                        productServiceClient.update(temp);
                    }

                }
                //在数据库中插入订单详情信息
                bookDetailDao.add(bookDetail);
            }
            return num;
        }
        return 0;
    }

    private void inspection(Book book) {
        //校验会员信息是否为空，为空则抛错
        if(book.getMemberId()==null){
            throw new CommonException(ExceptionEnum.PARAM_IS_NULL.getCode(),"会员不能为空");
        }else{
            //不为空，则查找到对应的会员信息，并设置到订单对象中
            Member member = memberServiceClient.findById(book.getMemberId());
            book.setMemberName(member.getName());
            book.setAddress(member.getAddress());
            book.setPhone(member.getPhone());
        }
        //以下对商品信息进行设置
        List<BookDetail> bookDetails =  book.getBookDetailList();
        for(BookDetail bookDetail : bookDetails){
            int productId= bookDetail.getProductId();
            //根据ID查询商品信息
            Product product = productServiceClient.findById(productId);
            if(product==null){
                throw  new CommonException(ExceptionEnum.PARAM_IS_NULL.getCode(),"没有这个商品");
            }else{
                //给订单详情对象设置对应的商品信息
                bookDetail.setProductName(product.getName());
                bookDetail.setProductImgs(product.getImgs());
                bookDetail.setProductPrice(product.getPrice());bookDetail.setItemPrice(bookDetail.getProductPrice().multiply(new BigDecimal(bookDetail.getProductCount())));

            }
            //校验商品库存
            if(product.getStock()==null||product.getStock()<=0){
                throw  new CommonException(ExceptionEnum.PARAM_IS_NULL.getCode(),"商品库存要大于0");
            }
        }

    }

    @Override
    public PageInfo<Book> findByQuery(BookQuery bookQuery) {
        PageInfo<Book> pageInfo=null;
        PageHelper.startPage(bookQuery.getPageNum(),bookQuery.getPageSize());
        List<Book> bookList = bookDao.findByQuery(bookQuery);
        for(Book book : bookList){
            List<BookDetail> bookDetailList = bookDetailDao.findByBookId(book.getId());
            book.setBookDetailList(bookDetailList);
        }
        Page<Book> page = (Page<Book>) bookList;
        pageInfo = page.toPageInfo();
        return pageInfo;
    }

    @Override
    public boolean update(Book book) {
        if (book.getId()!=null){
            if(book.getStatus()==1){
                book.setPayType(1);
                book.setPayTime(new Date());
                book.setPayNum(getOrderIdByUUId());
            }
            return 1== bookDao.update(book);
        }
        return false;
    }

}
