package com.conhj.control;

import com.conhj.congfig.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "InitControl",loadOnStartup = 1,urlPatterns = {"/load"})
public class InitControl extends HttpServlet {
    private static ApplicationContext ctx;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    public void init() throws ServletException {
        if(ctx==null){
            ctx=new AnnotationConfigApplicationContext(AppConfig.class);
        }
    }

    public static Object getBean(Class b){
        Object be=null;

        be=ctx.getBean(b);

        return be;
    }
    public static Object getBean(String beanName){
        return ctx.getBean(beanName);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
