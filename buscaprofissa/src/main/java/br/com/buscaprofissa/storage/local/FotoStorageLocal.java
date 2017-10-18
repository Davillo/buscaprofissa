package br.com.buscaprofissa.storage.local;

import static  java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


import br.com.buscaprofissa.storage.FotoStorage;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoStorageLocal implements FotoStorage {
	

	private Path local;
	private Path localTemporario;
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".buscaprofissa"));
	}
	
	public FotoStorageLocal(Path path) {
		this.local = path;
		criarPastas();
	}

	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
			}
		}
		
		return novoNome;
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			
				System.out.println("pastas criadas");
				System.out.println("pasta default : "+this.local.toAbsolutePath());
				System.out.println("pasta temporária : "+this.localTemporario.toAbsolutePath());
			
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
		}
	}
	
	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
	
		
		return novoNome;
		
	}

	@Override
	public byte[] recuperarFotoTemporaria(String nome) {

		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome)); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro lendo a foto temporária",e);
		}
	}

	@Override
	public void salvar(String foto) {

		
		try {
			Files.move(this.localTemporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro movendo a foto para destino final");
		}
		
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(80, 100).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro gerando a thumbnail");
		}
	}

	@Override
	public byte[] recuperar(String nome) {

		try {
			return Files.readAllBytes(this.local.resolve(nome)); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro lendo a foto",e);
		}
	}
	
	
}
