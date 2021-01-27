package com.curso.ejb;

import com.curso.model.Nota;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface NotaFacadeLocal {

    void create(Nota persona);

    void edit(Nota nota);

    void remove(Nota nota);

    Nota find(Object id);

    List<Nota> findAll();

    List<Nota> findRange(int[] range);

    int count();
    
    List<Nota> Buscar (int codigoPersona, int CodigoCategoria, Date FechaConsulta) throws Exception;
    
}
