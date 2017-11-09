package br.com.buscaprofissa.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.buscaprofissa.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.buscaprofissa.thymeleaf.processor.MessageElementTagProcessor;

public class BuscaProfissaDialect extends AbstractProcessorDialect {

	public BuscaProfissaDialect() {
		super("BuscaProfissa","buscaprofissa",StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
