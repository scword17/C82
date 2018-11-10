package com.conhj.service;

import com.conhj.dao.UserImpl;
import com.conhj.po.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    //private UserDAOImpl dao;

    @Autowired
    private UserImpl dao;

    public UserService(){
        //dao=new UserDAOImpl();
    }
    @Transactional(readOnly = true)
    public List<UserEntity> queryAll(){
        return dao.queryAll();
    }
    @Transactional
    public boolean addUser(UserEntity user){

        return dao.addUser(user);//data access object
    }
    @Transactional
    public boolean delUser(UserEntity user){
        return dao.delUser(user);//data access object

    }
    @Transactional(readOnly = true)
    public UserEntity queryOne(UserEntity user){
        return dao.queryOne(user);
    }
    @Transactional
    public boolean updateUser(UserEntity user){

        return dao.updateUser(user);
    }


}
