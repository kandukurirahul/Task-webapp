package com.rahul;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository implements TaskRepository {
    // Loads the mysql driver
    Connection con;
    Statement stmt;

    public DatabaseRepository() throws ClassNotFoundException {
        ConnectionEstablishment();
    }

    public void ConnectionEstablishment() throws ClassNotFoundException {
        // Connects to mysql service through a connection url and credentials
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "testuser", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }

// Mainly used for retrieval queries

    }

    @Override
    public void add(Task task) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = simpleDateFormat.format(task.getDueDate());
        try {
            stmt = con.createStatement();
            String q = "insert into task values(" + task.getTaskId() + ",'" + task.getTaskName() + "','" + task.getTaskDesc() + "','" + date
                    + "','" + task.getStatus() + "')";
            stmt.executeUpdate(q);
            //con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Task> displayIdandName() {
        //ConnectionEstablishment();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select taskid,name from task");
            return printIdName(rs);
            //con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Task> display() {
        //ConnectionEstablishment();

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from task");
            return createTask(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Task search(int id) {
        //ConnectionEstablishment();
        Task task = new Task();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from task where taskid=" + id);
            if (rs.next()) {

                task.setTaskId(rs.getInt(1));
                task.setTaskName(rs.getString(2));
                task.setTaskDesc(rs.getString(3));
                task.setDueDate(rs.getDate(4));
                task.setStatus(Taskstatus.valueOf(rs.getString(5)));
                //con.close();
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(int del) {
        int totalBefore = totalTask();
        int totalAfterDelete;

        //ConnectionEstablishment();
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("delete from task where taskid=" + del);
            totalAfterDelete = totalTask();
            //con.close();
            if (totalBefore == totalAfterDelete + 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Task> listByStatus(Taskstatus status) {
        //ConnectionEstablishment();
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from task where status='" + status + "'");
            return createTask(rs);
            //con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateStatus(int taskid, Taskstatus newStatus) {
        //ConnectionEstablishment();
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("update task set status='" + newStatus + "' where taskid=" + taskid);
            //con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int totalTask() {
        int total = 0;
        //ConnectionEstablishment();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from task");
            if (rs.next())
                total = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public ArrayList<Task> getPendingTask() {
        //ConnectionEstablishment();
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            stmt = con.createStatement();
            String status = "DONE";
            ResultSet rs = stmt.executeQuery("select * from task where not status='" + status + "' order by dueDate ASC");
            return createTask(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Task> getTodayTask() {
        //ConnectionEstablishment();
        ArrayList<Task> currentTask = new ArrayList<>();
        try {
            stmt = con.createStatement();
            java.util.Date date = new java.util.Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String today = simpleDateFormat.format(date);
            ResultSet rs = stmt.executeQuery("select * from task where duedate='" + today + "'");
            return createTask(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Task> printIdName(ResultSet rs) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt(1));
                task.setTaskName(rs.getString(2));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printAll(ResultSet rs) {
        try {
            System.out.println(" TaskId=" + rs.getInt(1) +
                    "\n TaskName:" + rs.getString(2) +
                    "\n TaskDescription:" + rs.getString(3) +
                    "\n Task DueDate:" + dateToString(rs.getDate(4), "dd/MM/yyyy") +
                    "\n Task Status:" + rs.getString(5) + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String dateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public ArrayList<Task> createTask(ResultSet rs) {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt(1));
                task.setTaskName(rs.getString(2));
                task.setTaskDesc(rs.getString(3));
                task.setDueDate(rs.getDate(4));
                task.setStatus(Taskstatus.valueOf(rs.getString(5)));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}