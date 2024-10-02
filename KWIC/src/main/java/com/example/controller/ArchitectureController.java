package com.example.controller;

import com.example.zhuChengXu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class ArchitectureController {

//    private ArrayList<String> kwicList = new ArrayList<String>();

    @Autowired
    private zhuChengXu kwic;
//    private ArrayList<String> kwicList;
//
//    @Autowired
//    public ArchitectureController(mp kwic) {
//        this.kwic = kwic;
//        this.kwicList = new ArrayList<>();
//    }


    @GetMapping("/main-program")
    @ResponseBody
    public String showMainProgram(@RequestParam("input") String input) {

        ArrayList<String> kwicList = kwic.kwicList;
        //每次点击完 重新清空
        kwicList.clear();

        String[] tokens = input.split("、");

        for(String s: tokens) {
            String[] token = s.split(" ");
            int count = token.length;
            for (int i = 0; i < count; i++) {
                StringBuilder lineBuffer = new StringBuilder();
                int index = i;
                for (int f = 0; f < count; f++) {
                    if (index >= count)
                        index = 0;
                    lineBuffer.append(token[index]);
                    lineBuffer.append(" ");
                    index++;
                }
                String tmp = lineBuffer.toString().trim();
                kwicList.add(tmp);
            }
        }
        kwic.alphabetizer();
        StringBuilder res = new StringBuilder();
        for(String s:kwicList){
            res.append(s+"\n");
        }
        System.out.println(res);
        return res.toString();
    }


    @GetMapping("/object-oriented")
    public String showObjectOriented() {
        return "object-oriented";
    }

    @GetMapping("/event-system")
    public String showEventSystem() {
        return "event-system";
    }

    @GetMapping("/pipe-filter")
    public String showPipeFilter() {
        return "pipe-filter";
    }
}