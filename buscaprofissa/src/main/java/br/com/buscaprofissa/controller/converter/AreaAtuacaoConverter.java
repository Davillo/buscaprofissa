package br.com.buscaprofissa.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.buscaprofissa.model.AreaAtuacao;

public class AreaAtuacaoConverter implements Converter<String, AreaAtuacao> {

	@Override
	public AreaAtuacao convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			AreaAtuacao areaAtuacao = new AreaAtuacao();
			areaAtuacao.setId(Long.valueOf(id));
			return areaAtuacao;
		}
		
		return null;
	}

}
