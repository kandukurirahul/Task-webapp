package com.rahul;

import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class Servlet extends HttpServlet {

    public Servlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TaskManager taskManager = new TaskManager();
            ArrayList<Task> tasks = taskManager.display();
            JSONArray json = new JSONArray(tasks);
            PrintWriter out = resp.getWriter();
            out.println(json);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tid = 1 + new Random().nextInt(90);
        String name = req.getParameter("Name");
        String description = req.getParameter("Description");
        String status = req.getParameter("Status");
        String date = req.getParameter("DueDate");

        Task task = new Task();
        task.setTaskId(tid);
        task.setTaskName(name);
        task.setTaskDesc(description);
        task.setStatus(Taskstatus.valueOf(status));

        try {
            task.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            TaskManager taskManager = new TaskManager();
            taskManager.add(task);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.setStatus(201);
    }
}