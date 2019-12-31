package com.rahul;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class TaskFileRepository implements TaskRepository{
    private static final String TASKS_JSON_FILE="/home/rahulp/Desktop/workspace/java/tasks.json";
    private ObjectMapper objectMapper=new ObjectMapper();
    ArrayList<Task> tasks;
    File file=new File(TASKS_JSON_FILE);
    public TaskFileRepository(){
        tasks=readFromFile();
    }
    private void writeToFile(ArrayList<Task> tasks) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new FileWriter(TASKS_JSON_FILE),tasks);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private ArrayList<Task> readFromFile() {
        //final File file = new File(TASKS_JSON_FILE);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, TaskList.class);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void add(Task task) {
        tasks.add(task);
        //System.out.println(task);
        writeToFile(tasks);
    }

    @Override
    public ArrayList<Task> displayIdandName() {
        return readFromFile();
    }

    @Override
    public ArrayList<Task> display() {
        return readFromFile();
    }

    @Override
    public Task search(int id) {
        for(Task str:tasks) {
            if (str.getTaskId()==id) {
                return str;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int del) {
        for(Task task:tasks){
            if(task.getTaskId()==del){
                tasks.remove(task);
                writeToFile(tasks);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Task> listByStatus(Taskstatus status) {
        return readFromFile();
    }

    @Override
    public void updateStatus(int taskid, Taskstatus newStatus) {
        for(Task str:tasks){
            if(str.getTaskId()==taskid){
                str.setStatus(newStatus);
                writeToFile(tasks);
            }
        }
    }
    @Override
    public int totalTask(){
        return tasks.size();
    }
    @Override
    public ArrayList<Task> getPendingTask(){
        ArrayList<Task> pendingTaskList=new ArrayList<>();
        for(Task str:tasks){
            if(str.getStatus().equals(Taskstatus.valueOf("CREATED"))||str.getStatus().equals(Taskstatus.valueOf("IN_PROGRESS")))
                pendingTaskList.add(str);

        }
        Collections.sort(pendingTaskList);
        return pendingTaskList;
    }
    @Override
    public ArrayList<Task> getTodayTask(){
        ArrayList<Task> currentTask=new ArrayList<>();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String today=simpleDateFormat.format(date);
        for(Task i:tasks){
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
