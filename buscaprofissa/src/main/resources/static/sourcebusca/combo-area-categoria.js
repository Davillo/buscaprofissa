var Busca = Busca || {};


Busca.ComboArea=  (function (){
	
	
	function ComboArea(){
		this.combo = $('#area');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboArea.prototype.iniciar = function (){
		this.combo.on('change', onAreaAlterado.bind(this));
	}
	
	function onAreaAlterado(){
		this.emitter.trigger('alterado',this.combo.val());
	}
	
	return ComboArea;
}());


Busca.ComboCategoria=  (function (){
	
	
	function ComboCategoria(comboArea){
		this.comboArea = comboArea;
		this.combo = $('#categoria');
	}
	
	ComboCategoria.prototype.iniciar = function (){
		this.comboArea.on('alterado',onAreaAlterado.bind(this));
	}
	
	function onAreaAlterado(evento, id){
		if(id){
			
		
		var resposta = $.ajax({
			url: '/buscaprofissa/categorias',
			method: 'GET',
			contentType: 'application/json',
			data: {'area': id },
			beforeSend: iniciarRequisicao.bind(this),
			complete: finalizarRequisicao.bind(this)
		});
		
			resposta.done(onBuscarCategoriasFinalizado.bind(this));
		}else{
			reset.call(this);
		}
		
	}
	
	
	function onBuscarCategoriasFinalizado(categorias){
		var options = [];
		categorias.forEach(function(categoria){
			options.push('<option value="' + categoria.id + '">' + categoria.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
	}
	
	function reset(){
		this.combo.html('<option value="">Selecione a Categoria</option>');
		this.combo.val('');
		this.combo.attr('disabled','disabled');
	}
	
	function iniciarRequisicao(){
		
	}
	
	function finalizarRequisicao(){
		
	}
	
	return ComboCategoria;
}());

	


	


$(function (){
	var comboArea = new Busca.ComboArea();
	comboArea.iniciar();
	
	var comboCategoria = new Busca.ComboCategoria(comboArea);
	comboCategoria.iniciar();
	
});