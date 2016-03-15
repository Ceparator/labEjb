/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.StatefulDAOImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Ceparator
 */
@Named(value = "statefulBean")
@RequestScoped
public class StatefulBean implements Serializable{
    
    @EJB
    private StatefulDAOImpl statefulDAOImpl;
    
    private String name;
    
    public StatefulBean() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String addBook() {
        String bookName = name; 
        statefulDAOImpl.addBook(bookName);
        return "/addBooks.xhtml";
    }
    
    public List<String> getBooks() {
        List<String> bookList = statefulDAOImpl.getBooks();
        return bookList;
    }
    
}
