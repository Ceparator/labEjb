/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Ceparator
 */

@Singleton
@LocalBean
@ApplicationScoped
@Startup
public class SomeInterfaceImpl implements SomeInterface, Serializable {
    
    private int length;

    @PostConstruct
    private void onCreate() {
        length = 10;
    }

    @Override
    public String checkLength(int l) {
        if (l <= length) {
            return " Do it fast!";
        }
        else{
            return "";
        }
    }
}
