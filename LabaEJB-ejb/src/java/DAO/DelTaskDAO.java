/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Task;
import javax.ejb.Remote;

/**
 *
 * @author Ceparator
 */
@Remote
public interface DelTaskDAO {
    int deleteTask(int idTask);
    int addTask(Task task);
}
