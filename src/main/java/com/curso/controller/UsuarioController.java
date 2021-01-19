package com.curso.controller;

import com.curso.ejb.UsuarioFacadeLocal;
import com.curso.model.Persona;
import com.curso.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named
@ViewScoped
@ManagedBean

public class UsuarioController implements Serializable{
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @Inject     
    private Usuario usuario;
    @Inject   
    private Persona persona;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }  
    
    public void registrar(){
        try {
            this.usuario.setCodigo(persona);
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso","Se registro"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error","Error en base de datos"));
        }
    }
    
    @PostConstruct
    public void init() {
//        usuario = new Usuario();
//        persona = new Persona();
    }
}
