package com.oghriffers.section02.annotation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/annotation-lifeCycle")
public class LifeCycleTestServelt extends HttpServlet {

    private  int serviceCount=1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("annotation 매핑 doGet() 메소드 호출:" + serviceCount++);
    }
}
