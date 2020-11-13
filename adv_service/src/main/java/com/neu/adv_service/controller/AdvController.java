package com.neu.adv_service.controller;

import com.neu.adv_service.domain.Adv;
import com.neu.adv_service.service.AdvService;
import com.neu.adv_service.utils.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("advs")
public class AdvController {

    @Autowired
    AdvService advService;

    @RequestMapping("{type}/advs")
    @ResponseBody
    public List<Adv> add(@PathVariable("type") int type, @RequestParam(defaultValue = "4") int size){
        return advService.findByType(type,size);
    }


}
