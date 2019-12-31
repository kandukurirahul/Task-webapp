package com.rahul;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TaskRepository {
    public void add(Task task);
    public ArrayList<Task> displayIdandName();
    public ArrayList<Task> display();
    public Task search(int id);
    public boolean delete(int del);
    public ArrayList<Task> listByStatus(Taskstatus status);
    public void updateStatus(int taskid,Taskstatus newStatus);
    public int totalTask();
    public ArrayList<Task> getPendingTask();
    public ArrayList<Task> getTodayTask();
}
