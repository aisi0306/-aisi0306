package com.kaola.demo.service;


import com.kaola.demo.model.ResultMap;

public interface LoginService {

    ResultMap login(String account, String password);

}
