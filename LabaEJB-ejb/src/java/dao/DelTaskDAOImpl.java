/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import model.Task;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

/**
 *
 * @author Ceparator
 */
@ConversationScoped
public class DelTaskDAOImpl implements DelTaskDAO, Serializable {

    @Resource(lookup = "jdbc/laba")
    private DataSource dataSource;

    @Inject
    Conversation conversation;
    private int count;

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

    @Override
    public int deleteTask(int idTask) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM task WHERE idTask=?";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idTask);
            statement.execute();
            endConversation();
            return count;
        } catch (Exception e) {
            throw new RuntimeException("Произошла ошибка во время вызова метода deleteTask", e);
        }
    }

    @Override
    public int addTask(Task task) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO task (name, description, due_date) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setTimestamp(3, new Timestamp(task.getDueDate().getTime()));
            stmt.executeUpdate();
            if (conversation.isTransient()) {
                conversation.begin();
            }
            return count++;
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred in addTask method", e);
        } finally {
            try {
                dataSource.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(TaskDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void endConversation() {
        count = 0;
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
}
