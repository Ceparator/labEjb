/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;

/**
 *
 * @author Ceparator
 */
@Named(value = "someBean")
@SessionScoped
public class SomeBean implements Serializable {

    private int chislo;

    @PostConstruct
    private void initializeBean() {
        chislo =5;
    }

    public int getChislo() {
        return chislo;
    }

    public void setChislo(int chislo) {
        this.chislo = chislo;
    }
    
    public void addChislo(){
        this.chislo ++;
    }

    public SomeBean() {
    }

}
