package br.com.buscaprofissa.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

public class BuscaProfissaDialect extends AbstractProcessorDialect {

	protected BuscaProfissaDialect() {
		super("BuscaProfissa","buscaprofissa",StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String arg0) {
		final Set<IProcessor> processadores = new HashSet<>();
		return processadores;
	}

}
