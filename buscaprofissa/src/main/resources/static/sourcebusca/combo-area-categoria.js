var Busca = Busca || {};


Busca.ComboArea = (function(){
	
	function ComboArea(){
		this.combo = $('#area');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboArea.prototype.iniciar = function(){
		this.combo.on('change',onAreaAlterada.bind(this));
	}
	
	function onAreaAlterada(){
		this.emitter.trigger('alterada', this.combo.val());
	}
	
	return ComboArea;
	
	
}());


Busca.ComboCategoria = (function(){
	
	function ComboCategoria(comboArea){
		this.comboArea = comboArea;
		this.combo = $('#categoria');
	}
	
	ComboCategoria.prototype.iniciar = function(){
		this.comboArea.on('alterada',onAreaAlterada.bind(this));
	}
	
	function onAreaAlterada(evento , idArea){
		if(idArea){
		 var resposta = $.ajax({
			url:  this.combo.data('url'),
			method: 'GET',
			contentType: 'application/json',
			data: {'area' : idArea},
			beforeSend: iniciarRequisicao.bind(this),
			complete: finalizarRequisicao.bind(this)
			
		 });
		 
		 resposta.done(onBuscarCategoriasFinalizado.bind(this));
		}
	}
	
	function onBuscarCategoriasFinalizado(categorias){
		var options = [];
		categorias.forEach(function(categoria) {
			options.push('<option value="' + categoria.id + '">' + categoria.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
	//	var options = [];
		//for(var i = 0 ; i<categoria.length; i++){
		
			//console.log('ok');
	//	}
		
	
		
		
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