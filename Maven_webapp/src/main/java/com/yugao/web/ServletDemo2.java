package com.yugao.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/demo2")
public class ServletDemo2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletDemo2 doGet");
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title> test </title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>"+ req.getRequestURL()  + req.getQueryString() + "</h1>");
        writer.println("</body>");
        writer.println("</html>");
//        writer.close();
        System.out.println(req.getParameter("name"));
        System.out.println(new String(req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletDemo2 doPost");
        this.doGet(req, resp);
    }
}
