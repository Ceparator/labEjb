/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.DelTaskDAOImpl;
import DAO.TaskDAO;
import DAO.TaskDAOImpl;
import Model.Task;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author Ceparator
 */
@Named(value = "addTaskBean")
@SessionScoped
public class AddTaskBean implements Serializable {

    @EJB
    private TaskDAOImpl taskDAOImpl;
    @EJB
    private DelTaskDAOImpl delTaskDAOImpl;

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
        this.c = delTaskDAOImpl.addTask(task);
        return "/index.xhtml";
    }

    public String toTheDelTask(int idTask) {
        this.editId = idTask;
        return "/delTask.xhtml";
    }

    public String delTask(int idTask) {
        this.c = delTaskDAOImpl.deleteTask(idTask);
        return "/index.xhtml";
    }
}
