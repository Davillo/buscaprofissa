package br.com.buscaprofissa.repository.helper.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);
		
		Sort sort = pageable.getSort();
		if (sort != null) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}
		
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro)); // retorna a página pronta
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
			
			criteria.add(Restrictions.isNotNull("categoria"));
			criteria.add(Restrictions.eq("ativo", true));
			
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
