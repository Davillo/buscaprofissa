package br.com.buscaprofissa.storage.local;

import static  java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import br.com.buscaprofissa.storage.FotoStorage;

public class FotoStorageLocal implements FotoStorage {
	
	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".buscaprofissafotos"));
	}
	
	public FotoStorageLocal(Path path){
		this.local = path;
		criarPastas();
	}

	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			System.out.println("Pastas criadas para salvar fotos! ");
			System.out.println("Pasta default : "+this.local.toAbsolutePath());
			System.out.println("Pasta temporária : "+this.localTemporario.toAbsolutePath());
		} catch (IOException e) {

			throw new RuntimeException("Erro criando pasta para salvar foto");
		}
		
	}

	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if(files != null && files.length > 0){
			MultipartFile arquivo = files[0];
			 novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária",e);
			}
		
		}
		
		return novoNome;
		
	}
	
	private String renomearArquivo(String nomeOriginal){
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		return novoNome;
		
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			
			throw new RuntimeException("Erro lendo a foto temporária",e);
		}
	}
	
}
