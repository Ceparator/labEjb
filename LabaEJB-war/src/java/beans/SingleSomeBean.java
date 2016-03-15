/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Ceparator
 */
@Named(value = "singleSomeBean")
@ApplicationScoped
public class SingleSomeBean implements Serializable{

    private int number;

    @PostConstruct
    private void initializeBean() {
        number =-4;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public void addNumber(){
        this.number ++;
    }
    
    public SingleSomeBean() {
    }
    
}
