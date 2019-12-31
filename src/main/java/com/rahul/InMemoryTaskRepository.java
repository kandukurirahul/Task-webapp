package com.rahul;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InMemoryTaskRepository implements TaskRepository {
    ArrayList<Task> list = new ArrayList<Task>();
    public void add(Task task)
    {
        list.add(task);
    }
    public ArrayList<Task> displayIdandName(){
        return list;
    }
    public ArrayList<Task> display()
    {
        return list;
    }
    public Task search(int id)
    {
        for(Task str:list) {
            if (str.getTaskId()==id) {
                return str;
            }
        }
        return null;
    }
    public boolean delete(int del) {
        for (Task str : list) {
            if (str.getTaskId()==del) {
                list.remove(str);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Task> listByStatus(Taskstatus status) {
        return list;
    }
    public void updateStatus(int taskid,Taskstatus newStatus){
        for(Task str:list){
            if(str.getTaskId()==taskid){
                str.setStatus(newStatus);
            }
        }
    }
    public int totalTask(){
        return list.size();
    }
    public ArrayList<Task> getPendingTask(){
        ArrayList<Task> pendingTaskList=new ArrayList<>();
        for(Task str:list){
            if(str.getStatus().equals(Taskstatus.valueOf("CREATED"))||str.getStatus().equals(Taskstatus.valueOf("IN_PROGRESS")))
                pendingTaskList.add(str);
        }
        return pendingTaskList;
    }
    public ArrayList<Task> getTodayTask(){
        ArrayList<Task> currentTask=new ArrayList<>();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String today=simpleDateFormat.format(date);
        for(Task i:list){
            try {
                if(i.getDueDate().equals(simpleDateFormat.parse(today)))
                    currentTask.add(i);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return currentTask;
    }
}
