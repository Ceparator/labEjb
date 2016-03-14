/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import DAO.SomeInterfaceImpl;
import DAO.TaskDAO;
import Model.Task;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Ceparator
 */
@Named(value = "taskBean")
@RequestScoped
public class TaskBean implements Serializable {

    @EJB
    private TaskDAO taskDAO;
    @EJB
    private SomeInterfaceImpl someInterfaceImpl;

    public List<Task> selectTask() {
        List<Task> list = taskDAO.listAllTasks();
        for (Task t : list) {
            int d = t.getDescription().length();
            String s = someInterfaceImpl.checkLength(d);
            t.setDescription(t.getDescription()+s);
            taskDAO.editTask(t);
        }
        return list;
    }

}
