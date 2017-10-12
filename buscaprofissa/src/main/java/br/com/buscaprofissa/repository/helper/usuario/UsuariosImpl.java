package br.com.buscaprofissa.repository.helper.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;

public class UsuariosImpl implements UsuariosQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> filtrar(UsuarioFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.isNotNull("categoria"));
		
		
	if(filtro != null){
			
			
			
			
			
		
			if(isCategoriaPresente(filtro)){ // filtrar pelo estilo caso selecionado
				criteria.add(Restrictions.eq("categoria", filtro.getCategoria())); // filtra pelo estilo
			}
			
			if(isCidadePresente(filtro)){
				criteria.add(Restrictions.eq("endereco.cidade",filtro.getCidade()));
			}
			
			
			
			
			
			
			
		
		}
		
		
		
		return criteria.list();
	}

	private boolean isCidadePresente(UsuarioFilter filtro) {
		return filtro.getCidade() != null && filtro.getCidade().getId() != null; 
	}

	private boolean isCategoriaPresente(UsuarioFilter filtro) {
		return filtro.getCategoria() != null && filtro.getCategoria().getId() != null; 
	}



}
