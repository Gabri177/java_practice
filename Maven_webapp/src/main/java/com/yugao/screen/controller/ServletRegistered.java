package com.yugao.screen.controller;

import com.yugao.screen.server.UserServer;
import com.yugao.screen.pojo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// 可以直接以/registered?username=...&password=... 进行测试
// 如果设置成功将会自动跳转到登录界面
@WebServlet("/registered")
public class ServletRegistered extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (UserServer.addUser(new User(req.getParameter("username"), req.getParameter("password")))){
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            PrintWriter writer = resp.getWriter();
            writer.write("false");
            writer.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doGet(req, resp);
    }
}
