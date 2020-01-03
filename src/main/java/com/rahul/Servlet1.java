package com.rahul;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet1 extends HttpServlet {
    public Servlet1() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("taskid"));
        try {
            TaskManager taskManager = new TaskManager();
            Task task = taskManager.search(id);
            PrintWriter out = resp.getWriter();
            if (task == null)
                resp.setStatus(404);
            else {
                out.println(task);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("taskId"));
        try {
            TaskManager taskManager = new TaskManager();
            if (!taskManager.delete(id))
                resp.setStatus(404);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("taskId"));
        String status = req.getParameter("newStatus");
        try {
            TaskManager taskManager = new TaskManager();
            Task task = new Task();
            task = taskManager.search(id);
            if (task == null)
                resp.setStatus(404);
            else
                taskManager.updateStatus(id, Taskstatus.valueOf(status));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
