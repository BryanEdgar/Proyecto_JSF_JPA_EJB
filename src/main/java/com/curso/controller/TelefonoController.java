/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.controller;

import com.curso.ejb.TelefonoFacadeLocal;
import com.curso.model.Telefono;
import com.curso.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class TelefonoController implements Serializable {

    @EJB
    private TelefonoFacadeLocal telefonoEJB;

    List<Telefono> telefonos;

    @Inject
    private Telefono telefono;

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    
    
    public TelefonoFacadeLocal getTelefonoEJB() {
        return telefonoEJB;
    }

    public void setTelefonoEJB(TelefonoFacadeLocal telefonoEJB) {
        this.telefonoEJB = telefonoEJB;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    @PostConstruct
    public void init() {
        telefonos = telefonoEJB.findAll();
    }

    public void registrar() {

        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); // obtengo la web session del login
            telefono.setPersona(us.getCodigo());
            telefonoEJB.create(telefono);
        } catch (Exception e) {

        }
    }

}
