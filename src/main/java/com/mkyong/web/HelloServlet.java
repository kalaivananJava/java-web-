package com.mkyong.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/customer")
public class HelloServlet extends HttpServlet {

    private BusDAO bus;

    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/Bus";
        bus = new BusDAO(jdbcURL, "root", "");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet call");

        List<Bus> busList = new ArrayList<Bus>();

        try {
            
            busList = bus.listAllCustomers();
        } catch (SQLException e) {

            e.printStackTrace();
        }

        String json = new Gson().toJson(busList);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Do post called");
        String requestData =req.getReader().lines().collect(Collectors.joining());
        Bus newBus=new Gson().fromJson(requestData,Bus.class);
        System.out.println("requestData length->" +requestData.length());
        System.out.println("requestData-> "+requestData);
        System.out.println(newBus.getId()+" "+newBus.getCustomer_name());
        System.out.println(newBus);

        try {
            bus.insert(newBus);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println("do delete called...");
        String requestData = req.getReader().lines().collect(Collectors.joining());
        Bus deletebus=new Gson().fromJson(requestData, Bus.class);
        System.out.println("requestData Length->" + requestData.length());
        System.out.println("requestData->" + requestData);
        System.out.println(deletebus.getId()+" "+deletebus.getBus_name());
       System.out.println(deletebus);

   try {
    bus.delete(deletebus.getId());
} catch (SQLException e) {
   
    e.printStackTrace();
}
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do put called....");
        String requestData = req.getReader().lines().collect(Collectors.joining());
        Bus updateCustomer = new Gson().fromJson(requestData, Bus.class);
        System.out.println("requestData Length->" + requestData.length());
        System.out.println("requestData->" + requestData);
        System.out.println(updateCustomer.getId()+" "+updateCustomer.getCustomer_name());

        try {
            bus.update(updateCustomer);
        } catch (SQLException e) {
            // 
            e.printStackTrace();
        }
 
    }
}
