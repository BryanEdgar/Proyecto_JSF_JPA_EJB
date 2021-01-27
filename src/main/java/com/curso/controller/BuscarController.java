package com.curso.controller;

import com.curso.ejb.CategoriaFacadeLocal;
import com.curso.ejb.NotaFacadeLocal;
import com.curso.model.Categoria;
import com.curso.model.Nota;
import com.curso.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
//@ViewScoped
@SessionScoped
public class BuscarController implements Serializable {

    @EJB
    private CategoriaFacadeLocal categoriaEJB;

    @EJB
    private NotaFacadeLocal notaEJB;

    private List<Categoria> listaCategorias;
    private List<Nota> listaNotas;

    private int codigoCategoria;
    private Date fechaCategoria;

    FacesContext context = FacesContext.getCurrentInstance();

    public List<Nota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }
    
    
    
    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public Date getFechaCategoria() {
        return fechaCategoria;
    }

    public void setFechaCategoria(Date fechaCategoria) {
        this.fechaCategoria = fechaCategoria;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    @PostConstruct
    public void init() {
        listaCategorias = categoriaEJB.findAll();
    }

    public void buscar() {
        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); // obtengo la web session del login
            listaNotas = notaEJB.Buscar(us.getCodigo().getCodigo(), codigoCategoria, fechaCategoria);
        } catch (Exception e) {
        }
    }
}
