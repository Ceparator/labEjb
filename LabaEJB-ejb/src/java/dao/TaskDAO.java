/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Task;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ceparator
 */
@Remote
public interface TaskDAO {

    List<Task> listAllTasks();

    Task getTaskById(Integer taskId);

    //Task addTask(Task task);

    boolean editTask(Task task);

}
