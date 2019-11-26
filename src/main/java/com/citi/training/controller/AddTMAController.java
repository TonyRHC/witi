package com.citi.training.controller;


import com.citi.training.pojos.TMAPojo;

import com.citi.training.utils.TMADatabaseAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AddTMAController {

    @Autowired TMADatabaseAdder tmaDatabaseAdder;

    @RequestMapping(value="/addTMA", method= RequestMethod.POST)
    public String addTMAStrategy(
            @RequestBody TMAPojo TMARequest) {
        System.out.println("Add request: " + TMARequest.toString());

        //Add to database
        tmaDatabaseAdder.addTMA(TMARequest);

        return TMARequest.toString();
    }
}
