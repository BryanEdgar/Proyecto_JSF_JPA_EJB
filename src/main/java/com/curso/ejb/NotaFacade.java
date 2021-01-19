
package com.curso.ejb;

import com.curso.model.Nota;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class NotaFacade extends AbstractFacade<Nota> implements NotaFacadeLocal{
    

    @PersistenceContext(unitName = "PrimePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotaFacade() {
        super(Nota.class);
    }
          
}
