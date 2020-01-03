package com.rahul;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
        // write your code here
        int tid;
        TaskManager taskManager = new TaskManager();
        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println("1.Add\n2.IdandName\n3.Display\n4.Search\n5.Delete\n6.ListByStatus\n" +
                    "7.Update status\n8.TotalTasks\n9.PendingTasks\n10.Today's Task\n11.Exit");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Your Choice");

            int choice = Integer.parseInt(br.readLine());
            switch (choice) {

                case 1://add
                    //entering task name
                    System.out.println("enter name to add:");
                    String taskname = br.readLine();

                    //entering Description of task
                    System.out.println("enter description to add:");
                    String description = br.readLine();

                    //entering duedate
                    System.out.println("enter date to add(dd/MM/yyyy):");
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine());

                    //entering status
                    System.out.println("enter status to add:(CREATED/IN_PROGRESS/DONE");
                    Taskstatus status = Taskstatus.valueOf(br.readLine());

                    tid = 1 + new Random().nextInt(90);

                    Task task = new Task(tid, taskname, description, date, status);
                    taskManager.add(task);
                    break;

                case 2://id and name

                    ArrayList<Task> list = taskManager.displayIdandName();
                    System.out.println("Taskid      TaskName");

                    for (Task str : list)
                        System.out.println(str.getTaskId() + " " + str.getTaskName());
                    break;

                case 3://displayall

                    ArrayList<Task> displaylist = taskManager.display();
                    for (Task str : displaylist)
                        System.out.println(str);
                    break;
                case 4://search

                    System.out.println("enter id to search");
                    int id = Integer.parseInt(br.readLine());
                    task = taskManager.search(id);
                    if (task == null)
                        System.out.println("taskid:" + id + " is not found");
                    else {
                        System.out.println("taskid:" + id + " is found");
                        System.out.println(task);
                    }
                    break;

                case 5://delete

                    System.out.println("enter id to delete");
                    int deleteid = Integer.parseInt(br.readLine());
                    if (taskManager.delete(deleteid))
                        System.out.println("taskid:" + deleteid + " is deleted");
                    else
                        System.out.println("taskid:" + deleteid + " is not found");
                    break;


                case 6://listByStatus

                    System.out.println("enter status to search(CREATED/IN_PROGRESS/DONE)");
                    Taskstatus str = Taskstatus.valueOf(br.readLine());
                    ArrayList<Task> statuslist = taskManager.listByStatus(str);
                    for (Task i : statuslist) {
                        if (i.getStatus().equals(str)) {
                            System.out.println(i);
                        }
                    }
                    break;

                case 7://update status

                    System.out.println("enter id to update status of task");
                    int taskId = Integer.parseInt(br.readLine());
                    System.out.println("enter new Status to update");
                    Taskstatus newStatus = Taskstatus.valueOf(br.readLine());
                    taskManager.updateStatus(taskId, newStatus);
                    break;

                case 8://total status

                    System.out.println("Total Tasks=" + taskManager.totalTask());
                    break;

                case 9://pending tasks

                    System.out.println("Pending Tasks:");
                    System.out.println(taskManager.getPendingTask());
                    break;

                case 10://todays task

                    System.out.println("Todays Tasks are");
                    ArrayList<Task> todayTask = taskManager.getTodayTask();
                    System.out.println(todayTask);
                    break;

                case 11://exit

                    System.exit(0);
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}