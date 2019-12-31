package com.rahul;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskManager {
    TaskRepository repository=new DatabaseRepository();

    public TaskManager() throws ClassNotFoundException {
    }

    public void add(Task task){
        repository.add(task);
    }
    public ArrayList<Task> displayIdandName(){
        return repository.displayIdandName();
    }
    public ArrayList<Task> display(){
        return repository.display();
    }
    public Task search(int id){
        return repository.search(id);
    }
    public boolean delete(int del){
        return repository.delete(del);
    }
    public ArrayList<Task> listByStatus(Taskstatus status){
        return repository.listByStatus(status);
    }
    public void updateStatus(int taskid,Taskstatus newStatus){
        repository.updateStatus(taskid,newStatus);
    }
    public int totalTask(){
        return repository.totalTask();
    }
    public ArrayList<Task> getPendingTask(){
        return repository.getPendingTask();
    }
    public ArrayList<Task> getTodayTask(){
        return repository.getTodayTask();
    }
}

