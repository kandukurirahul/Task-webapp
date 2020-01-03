package com.rahul;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Task implements Comparable<Task> {
    private int taskId;
    private String taskName;
    private String taskDesc;
    private Date dueDate;
    private Taskstatus status;

    public Task() {

    }

    @Override
    public String toString() {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        return
                ("taskid : " + taskId +
                        " taskname : " + taskName +
                        " taskDesc : " + taskDesc +
                        " duedate : " + time.format(dueDate) +
                        " status : " + status + '\n');
    }

    Task(int taskId, String task, String des, Date td, Taskstatus status) {
        this.taskId = taskId;
        this.taskName = task;
        this.taskDesc = des;
        this.dueDate = td;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Taskstatus getStatus() {
        return status;
    }

    public void setStatus(Taskstatus status) {
        this.status = status;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int compareTo(Task task) {
        if (this.getDueDate().compareTo(task.getDueDate()) == 0) {
            if (this.getStatus().compareTo(task.getStatus()) == 0)
                return 0;
            else if (this.getStatus().compareTo(task.getStatus()) < 0)
                return -1;
            else
                return 1;
        } else if (this.getDueDate().compareTo(task.getDueDate()) < 0)
            return -1;
        else
            return 1;
    }
}
