package com.kaola.demo.meta;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String account;

    private String password;

    private int userType;//0卖家，1卖家
}
