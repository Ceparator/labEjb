/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DelTaskDAOImpl;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Task;

/**
 *
 * @author Ceparator
 */
@Named(value = "addTaskBean")
@SessionScoped
public class AddTaskBean implements Serializable {

    @EJB
    private DelTaskDAOImpl dao;

    @PostConstruct
    private void initializeBean() {
        c = 0;
    }

    private Task task;
    private int c;
    private int editId;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void AddTaskBean() {
        task = new Task();
    }

    public String adTask() {
        java.sql.Date newDate = new java.sql.Date(task.getDueDate().getTime());
        task.setDueDate(newDate);
        this.c = dao.addTask(task);
        return "/index.xhtml";
    }

    public String toTheDelTask(int idTask) {
        this.editId = idTask;
        return "/delTask.xhtml";
    }

    public String delTask(int idTask) {
        this.c = dao.deleteTask(idTask);
        return "/index.xhtml";
    }
}
