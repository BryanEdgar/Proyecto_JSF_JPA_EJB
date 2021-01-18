/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.controller;

import com.curso.model.Usuario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author user
 */ 
@Named
@ViewScoped
public class plantillaController implements Serializable{
    public void verificarSesion(){
    FacesContext context = FacesContext.getCurrentInstance();
        try {
        Usuario us =   (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if (us==null) {
              context.getExternalContext().redirect("./../permisos.xhtml");
            } 
        } catch (Exception e) {
        }
    }
}
