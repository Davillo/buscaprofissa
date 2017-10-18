package br.com.buscaprofissa.repository.helper.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.buscaprofissa.model.Usuario;
import br.com.buscaprofissa.repository.filter.UsuarioFilter;

public class UsuariosImpl implements UsuariosQueries {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);
		criteria.add(Restrictions.isNotNull("categoria"));

		int totalRegistrosPagina = pageable.getPageSize();
		int paginaAtual = pageable.getPageNumber();
		int primeiroRegistro = paginaAtual * totalRegistrosPagina;

		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPagina);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	
	private Long total(UsuarioFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());

		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(UsuarioFilter filtro, Criteria criteria) {
		if (filtro != null) {
					
			
			if(!StringUtils.isEmpty(filtro.getDescricaoProfissional())) {
				criteria.add(Restrictions.ilike("descricaoProfissional", filtro.getDescricaoProfissional(), MatchMode.ANYWHERE));
			}
			
			
			if (isCategoriaPresente(filtro)) { // filtrar pelo estilo caso selecionado
				criteria.add(Restrictions.eq("categoria", filtro.getCategoria())); // filtra pelo estilo
			}

			if (isCidadePresente(filtro)) {
				criteria.add(Restrictions.eq("endereco.cidade", filtro.getCidade()));
			}
			
			
		
		}
	}

	

	private boolean isCidadePresente(UsuarioFilter filtro) {
		return filtro.getCidade() != null && filtro.getCidade().getId() != null;
	}

	private boolean isCategoriaPresente(UsuarioFilter filtro) {
		return filtro.getCategoria() != null && filtro.getCategoria().getId() != null;
	}

}
