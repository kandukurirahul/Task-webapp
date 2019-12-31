package com.rahul;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Servlet extends HttpServlet {

    public Servlet(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TaskManager taskManager = new TaskManager();
            ArrayList<Task> tasks=taskManager.display();
            JSONArray json=new JSONArray(tasks);
            PrintWriter out=resp.getWriter();
            out.println(json);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
