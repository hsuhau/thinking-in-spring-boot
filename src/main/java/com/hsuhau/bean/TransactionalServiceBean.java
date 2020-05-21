package com.hsuhau.bean;

import com.hsuhau.annotation.TransactionalService;

@TransactionalService
//@TransactionalService(name = "transactionalServiceBean")
public class TransactionalServiceBean {
    public void save() {
        System.out.println("保存操作...");
    }
}
