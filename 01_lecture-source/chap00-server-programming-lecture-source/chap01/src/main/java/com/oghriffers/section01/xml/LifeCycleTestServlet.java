package com.oghriffers.section01.xml;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LifeCycleTestServlet extends HttpServlet {
    private  int initCount=1;
    private  int serviceCount=1;
    private  int destoryCount=1;

    public LifeCycleTestServlet(){
        System.out.println("LifeCycleTest 인스턴스 생성됨");
    }
    @Override
    public void init() throws ServletException { //최초의 한번만 실행되는 것
        System.out.println("xml 매핑 inint() 매소드호출 :" + initCount++);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //호출할때마다 실행
        System.out.println("xml 매핑 goGet() 메소드 호출:" + serviceCount++);
    }

    @Override
    public void destroy() {
        //종료할때 실행
        System.out.println("xml 매핑 destory() 매소드호출 :" + destoryCount++);
    }
}
