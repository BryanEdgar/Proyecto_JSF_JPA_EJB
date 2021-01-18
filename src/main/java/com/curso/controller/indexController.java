/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.controller;

import com.curso.ejb.UsuarioFacadeLocal;
import com.curso.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author user
 */
@Named
@ViewScoped
@ManagedBean
public class indexController implements Serializable {

    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String iniciarSesion() {
        Usuario us;
        String redireccion = null;
        try {
            us = EJBUsuario.iniciarSesion(usuario);
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                redireccion = "protegido/principal?faces-redirect=true";
            } else {
                    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenciales incorrectas"));
            }
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error","Error en la base de datos"));
        }
        return redireccion;
    }
}
