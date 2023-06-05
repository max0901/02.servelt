package com.ohgiraffers.section01.servicemethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/request-service")//value를 써도 되고 안써도되지만 다른설정한다고하면 value를 쓴단
public class ServiceMethodTestServelet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service()메소드 호출됨 " );
        
        String httpMethod=req.getMethod();
        System.out.println("httpMethod = " + httpMethod);

        if("GET".equals(httpMethod)){
//            httpMethod.equals("GET") 위에와 같지만 method에 null이들어가있으면 밑에는 non나오는 오류가된다
        doGet(req,resp);


        } else if ("POST".equals(httpMethod)) {
            doPost(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET 요청을 처리할 메소드 호출");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST요청을 처리할 메소드 호출");
    }
}
