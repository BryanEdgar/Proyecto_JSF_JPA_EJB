/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author user
 */
@Entity
@Table(name = "nota")
public class Nota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ManyToOne // para hacer la realcion de clave foranea 
    @JoinColumn(name = "codigo_persona", nullable = false)
    private Persona persona;

    @ManyToOne // para hacer la realcion de clave foranea 
    @JoinColumn(name = "codigo_categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "encabezado")
    private String encabezado;

    @Column(name = "cuerpo")
    private String cuerpo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha",insertable = false)
    private Date fecha;

    @Column(name = "comentarioAdmin")
    private String comentarioAdm;

    @Column(name = "valorizacion")
    private Integer valorizacion = 1;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarioAdm() {
        return comentarioAdm;
    }

    public void setComentarioAdm(String comentarioAdm) {
        this.comentarioAdm = comentarioAdm;
    }

    public Integer getValorizacion() {
        return valorizacion;
    }

    public void setValorizacion(Integer valorizacion) {
        this.valorizacion = valorizacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nota other = (Nota) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nota{" + "codigo=" + codigo + '}';
    }

    
}
