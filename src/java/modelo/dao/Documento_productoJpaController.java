/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Documento_producto;

/**
 *
 * @author luisn
 */
public class Documento_productoJpaController implements Serializable {

    public Documento_productoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documento_producto documento_producto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(documento_producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Documento_producto documento_producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            documento_producto = em.merge(documento_producto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = documento_producto.getId_producto();
                if (findDocumento_producto(id) == null) {
                    throw new NonexistentEntityException("El Documento_producto con id " + id + " no existe.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento_producto documento_producto;
            try {
                documento_producto = em.getReference(Documento_producto.class, id);
                documento_producto.getId_producto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El Documento_producto con id " + id + " no existe.", enfe);
            }
            
            em.remove(documento_producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Documento_producto> findDocumento_productoEntities() {
        return Documento_productoJpaController.this.findDocumento_productoEntities(true, -1, -1);
    }

    public List<Documento_producto> findDocumento_productoEntities(int maxResults, int firstResult) {
        return Documento_productoJpaController.this.findDocumento_productoEntities(false, maxResults, firstResult);
    }

    private List<Documento_producto> findDocumento_productoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento_producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Documento_producto findDocumento_producto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Documento_producto.class, id);
        } finally {
            em.close();
        }
    }
  
    
}
