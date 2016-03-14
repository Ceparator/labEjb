/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

/**
 *
 * @author Ceparator
 */

@ApplicationScoped
@Remote(DelTaskDAO.class)
public class DelTaskDAOImpl implements DelTaskDAO{

    @Resource(lookup = "jdbc/laba")
    private DataSource dataSource;

    @Override
    public boolean deleteTask(int idTask) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM task WHERE idTask=?";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idTask);
            statement.execute();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Произошла ошибка во время вызова метода deleteTask", e);
        }
    }
}
