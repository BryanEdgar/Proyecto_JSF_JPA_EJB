package com.curso.controller;

import com.curso.ejb.CategoriaFacadeLocal;
import com.curso.ejb.NotaFacadeLocal;
import com.curso.model.Categoria;
import com.curso.model.Nota;
import com.curso.model.Persona;
import com.curso.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
@ManagedBean
public class NotaController implements Serializable {

    @EJB
    private NotaFacadeLocal notaEJB;

    @EJB
    private CategoriaFacadeLocal categoriaEJB;

    @Inject
    private Nota nota;

    @Inject
    private Categoria categoria;

    private List<Categoria> categorias;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void registrar() {

        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); // obtengo la web session del login
            nota.setCategoria(categoria);
            nota.setPersona(us.getCodigo());
            notaEJB.create(nota);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro la nota"));
        } catch (Exception e) {
        }
    }

    @PostConstruct
    public void init() { 
        categorias = categoriaEJB.findAll();
    }
}
