
package com.curso.ejb;

import com.curso.model.Nota;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

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

    @Override
    public List<Nota> Buscar(int codigoPersona, int CodigoCategoria, Date FechaConsulta) throws Exception{
      List<Nota> lista;
        try {
            
            String jpql = "FROM Nota n WHERE n.persona.codigo = ?1 and n.categoria.codigo = ?2 and n.fecha between ?3 and ?4";
            Query query = em.createQuery(jpql);
            query.setParameter(1, codigoPersona);
            query.setParameter(2, CodigoCategoria);
            query.setParameter(3, FechaConsulta,TemporalType.DATE);
            Calendar cal = Calendar.getInstance();
            cal.setTime(FechaConsulta);
            cal.add(Calendar.DATE,1);
            query.setParameter(4,cal,TemporalType.DATE);
            lista = query.getResultList();
            
        } catch (Exception e) {
            throw e;
        }
     return lista;   
    } 
      
}
