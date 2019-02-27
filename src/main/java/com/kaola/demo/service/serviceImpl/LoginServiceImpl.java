package com.kaola.demo.service.serviceImpl;


import com.kaola.demo.mapper.UserMapper;
import com.kaola.demo.enums.CodeMsg;
import com.kaola.demo.meta.User;
import com.kaola.demo.model.ResultMap;
import com.kaola.demo.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LoginServiceImpl implements LoginService {

    private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Override
    public ResultMap login(String account, String password) {
        if(StringUtils.isEmpty(account)){
            return ResultMap.genResultMap(CodeMsg.ACC_NOT_EXISTS);
        }
        User user = userMapper.getUserByAccount(account);
        if(null == user){
            return ResultMap.genResultMap(CodeMsg.ACC_NOT_EXISTS);
        }
        if(password != user.getPassword()){
            return ResultMap.genResultMap(CodeMsg.PWS_ERROR);
        }
        ResultMap resultMap = ResultMap.genResultMap(CodeMsg.SUCCESS);
        resultMap.setData(user);
        return resultMap;
    }

}
