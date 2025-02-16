package com.yugao.screen.controller;

import com.yugao.screen.server.UserServer;
import com.yugao.screen.pojo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 进入/login.html 进行登录测试, 如果登录成功会返回true 如果登录失败会返回false
@WebServlet("/login")
public class ServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (UserServer.isAccessable(new User(req.getParameter("username"), req.getParameter("password")))){
            resp.getWriter().print("true");
        } else {
            resp.getWriter().print("false");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doGet(req, resp);
    }
}
