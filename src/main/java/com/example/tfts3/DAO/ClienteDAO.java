// package com.example.tfts3.DAO;

// import java.util.List;

// import org.springframework.stereotype.Repository;

// import com.example.tfts3.Models.Cliente;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;

// @Repository
// public class ClienteDAO implements IClienteDAO{
	
// 	@PersistenceContext
// 	private EntityManager em;

// 	@SuppressWarnings("unchecked")	
// 	@Override
// 	public List<Cliente> findall() {
// 		return em.createQuery("from Cliente").getResultList();
// 		}

// 	@Override
// 	public void save(Cliente cliente) {
// 		if(cliente.getCod()!=null && cliente.getCod()>0) {
// 			// ACTUALIZAR 
// 			em.merge(cliente);
// 		}else {
// 			// GUARDAR
// 			em.persist(cliente);
// 		}		
		
// 	}

// 	@Override
// 	public Cliente findOne(Long cod) {
// 		return em.find(Cliente.class, cod);
// 	}

// 	@Override
// 	public void delete(Long cod) {
// 		em.remove(findOne(cod));
		
// 	}

// }
