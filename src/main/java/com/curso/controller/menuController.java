/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.controller;

import com.curso.ejb.MenuFacadeLocal;
import com.curso.model.Menu;
import com.curso.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author user
 */
@Named
@SessionScoped
//@ViewScoped
@ManagedBean
public class menuController implements Serializable {

    @EJB
    private MenuFacadeLocal EJBMenu;
    private List<Menu> lista;
    private MenuModel model;

    @PostConstruct
    public void init() {
        this.listarMenus();
        model = new DefaultMenuModel();
        this.establecerPermiso();

    }

    public void listarMenus() {
        try {
            lista = EJBMenu.findAll();
        } catch (Exception e) {
        }
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    
    
    public void establecerPermiso() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); // obtengo la web session del login
        
        for (Menu m : lista) {
            if (m.getTipo().equals("S") && m.getTipoUsuario().equals(us.getTipo()) ) {
                DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label(m.getNombre()).build();
                for (Menu i : lista) {
                    Menu submenu = i.getSubmenu();
                    if (submenu != null) {
                        if (submenu.getCodigo() == m.getCodigo()) {
                            DefaultMenuItem item = DefaultMenuItem.builder().value(i.getNombre()).url("http://www.primefaces.org").icon("pi pi-home").build();
                            firstSubmenu.getElements().add(item);
                        }
                    }
                }
                model.addElement(firstSubmenu);
            } else {
                if(m.getSubmenu() == null && m.getTipoUsuario().equals(us.getTipo())){
                 DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                model.addElement(item);
                } 
            }
        }

    }
    
    public void cerrarSesion(){
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
