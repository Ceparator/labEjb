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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author Ceparator
 */
@Named(value = "addTaskBean")
@ConversationScoped
public class AddTaskBean implements Serializable {

    @EJB
    private TaskDAOImpl taskDAOImpl;
    @EJB
    private DelTaskDAOImpl delTaskDAOImpl;

    private int count;
    private Task task;
    private int editId;

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

    @PostConstruct
    public void init() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Inject
    Conversation conversation;

    public void adTask() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
        taskDAOImpl.addTask(task);
        count++;
    }
    
    public String toTheDelTask(int idTask){
        this.editId = idTask;
        return "/delTask.xhtml";
    }

    public void delTask(int idTask) {
        delTaskDAOImpl.deleteTask(idTask);
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
}
