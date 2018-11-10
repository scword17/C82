package com.conhj.dao;



import com.conhj.po.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserImpl {
//    private Configuration cfg=null;


    //
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public UserImpl(){
//        cfg=new Configuration().configure("hibernate.cfg.xml");
//        sf=cfg.buildSessionFactory();
    }
    public boolean addUser(UserEntity user)  {;
        boolean flag=false;
        Session session=sessionFactory.getCurrentSession();
        //     Transaction transaction=session.beginTransaction();
        try{
            session.save(user);
            //  transaction.commit();
            flag=true;
        }catch(Exception e){
            //  transaction.rollback();
            e.printStackTrace();
        }finally {
            //      session.close();

        }
        return flag;
    }

    public boolean updateUser(UserEntity user)  {
        Session session=sessionFactory.getCurrentSession();
//       Transaction transaction=session.beginTransaction();
        boolean flag=false;
        try{
            session.update(user);
//            transaction.commit();
            flag=true;
        }catch(Exception e){
//            transaction.rollback();
            e.printStackTrace();
        }finally {
            //  session.close();

        }
        return flag;
    }
    public boolean delUser(UserEntity user)  {
        Session session=sessionFactory.getCurrentSession();
//        Transaction transaction=session.beginTransaction();

        boolean flag=false;
        try{
            session.delete(user);
//            transaction.commit();
            flag=true;
        }catch(Exception e){
//            transaction.rollback();
            e.printStackTrace();
        }finally {
            //       session.close();
        }
        return flag;
    }
    public List<UserEntity> queryAll() {
        Session session=sessionFactory.getCurrentSession();

        Query query2=session.createQuery("from UserEntity as u order by u.id desc");
        List<UserEntity>list=null;
        query2.setCacheable(true);
        list=query2.list();
        //session.close();
        return list;
    }

    public UserEntity queryOne(UserEntity userFromWeb) {
        Session session=sessionFactory.getCurrentSession();
        int xid= userFromWeb.getId();


//        UserEntity user =session.load(UserEntity.class,xid);
//        return user;
        UserEntity user=(UserEntity) session.load(UserEntity.class,xid);

        UserEntity user2=new UserEntity();
        user2.setId(user.getId());
        user2.setUsername(user.getUsername());
        user2.setPassword(user.getPassword());
        //  session.close();
        return user2;
    }





}


