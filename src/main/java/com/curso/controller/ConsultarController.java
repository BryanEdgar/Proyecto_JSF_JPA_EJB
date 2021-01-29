/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.controller;

import com.curso.ejb.PersonaFacadeLocal;
import com.curso.ejb.TelefonoFacadeLocal;
import com.curso.model.Persona;
import com.curso.model.Telefono;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ConsultarController implements Serializable {

    @EJB
    private PersonaFacadeLocal personaEJB;

    @EJB
    private TelefonoFacadeLocal telefonoEJB;

    private int codigoPersona;

    List<Persona> personas;
    List<Telefono> telefonos;

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public int getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(int codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public void buscarTelefonos() throws Exception {
        try {
            telefonos = telefonoEJB.buscarTelefono(codigoPersona);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostConstruct
    public void init() {
        personas = personaEJB.findAll();
    }
}
