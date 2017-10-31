package br.com.buscaprofissa.storage.local;

import static  java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import br.com.buscaprofissa.storage.FotoStorage;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;


@Profile("local")
@Component
public class FotoStorageLocal implements FotoStorage {
	

	private Path local;
	
	public FotoStorageLocal() {
		this(getDefault().getPath(System.getenv("HOME"), ".buscaprofissa"));
	}
	
	public FotoStorageLocal(Path path) {
		this.local = path;
		criarPastas();
	}

	@Override
	public String salvar(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.local.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto", e);
			}
		}
		
		
		try {
			Thumbnails.of(this.local.resolve(novoNome).toString()).size(160, 160).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro gerando a thumbnail");
		}
		
		return novoNome;
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			
			
				System.out.println("pastas criadas");
				System.out.println("pasta default : "+this.local.toAbsolutePath());
			
		} catch (IOException e) {
			throw new RuntimeException("Erro criando pasta para salvar foto", e);
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

	@Override
	public String getUrl(String foto) {
		// TODO Auto-generated method stub
		return "http://localhost:9090/buscaprofissa/fotos/"+foto;
	}
	
	
}
