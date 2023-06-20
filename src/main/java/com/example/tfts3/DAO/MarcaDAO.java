// package com.example.tfts3.DAO;

// import java.util.List;

// import org.springframework.stereotype.Repository;

// import com.example.tfts3.Models.Marca;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;

// @Repository
// public class MarcaDAO implements IMarcaDAO{
    
//     @PersistenceContext
//     private EntityManager em;

//     @SuppressWarnings("unchecked")
//     @Override
//     public List<Marca> findMarcas(){
//         return em.createQuery("from Marca").getResultList();
//     }

//     @Override
//     public void save(Marca marca){
//         if (marca.getId()!=null && marca.getId()>0) {
//             // ACTUALIZAR        
//             em.merge(marca);
//         }else {
//             //GUARDAR
//             em.persist(marca);
//         }
//     }

//     @Override
//     public Marca findOne(Long id){
//         return em.find(Marca.class, id);
//     }

//     @Override
//     public void delete(Long id){
//         em.remove(findOne(id));
//     }
// }
