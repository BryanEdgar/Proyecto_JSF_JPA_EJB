
package com.curso.controller;

import com.curso.ejb.NotaFacadeLocal;
import com.curso.model.Nota;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
//import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@RequestScoped

public class comentarController implements Serializable{
    
    @EJB
    private NotaFacadeLocal notaEJB;
    
    private Nota notaa;

    public Nota getNotaa() {
        return notaa;
    }

    public void setNotaa(Nota notaa) {
        this.notaa = notaa;
    }
    
    
    
    private List<Nota> nota; //aqui se va almacenar las notas que vamos a mandar a recuperar en el Start

    public List<Nota> getNota() {
        return nota;
    }

    public void setNota(List<Nota> nota) {
        this.nota = nota;
    }
    
    
    
    
    
    @PostConstruct //es como el evento start .. 
    public void init(){
    nota = notaEJB.findAll(); //aqui estoy haciendo un select de todo lo q esta en el EJB
    }
    
    
    public void asignar(Nota notaa){
    this.notaa = notaa;
    }
}
