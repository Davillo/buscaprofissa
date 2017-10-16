var Busca = Busca || {};


Busca.ComboEstado = (function(){
	
	function ComboEstado(){
		this.combo = $('#estado');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboEstado.prototype.iniciar = function(){
		this.combo.on('change',onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado(){
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboEstado;
	
	
}());


Busca.ComboCidade = (function(){
	
	function ComboCidade(comboEstado){
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
	}
	
	ComboCidade.prototype.iniciar = function(){
		this.comboEstado.on('alterado',onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado(evento , idEstado){
		if(idEstado){
		 var resposta = $.ajax({
			url:  this.combo.data('url'),
			method: 'GET',
			contentType: 'application/json',
			data: {'estado' : idEstado},
			beforeSend: iniciarRequisicao.bind(this),
			complete: finalizarRequisicao.bind(this)
			
		 });
		 
		 resposta.done(onBuscarCidadesFinalizado.bind(this));
		}
	}
	
	function onBuscarCidadesFinalizado(cidades){
	//	var options = [];
		//cidades.forEach(function(cidade){
			//options.push('')
		//});
		
		var options = [];
		for(var i = 0 ; i<cidade.length; i++){
			options.push('<option value="'+cidade.id+'">"'+cidade.nome+'</option>');
			console.log('ok');
		}
		
	
		this.combo.removeAttr('disabled');
		
	}
	
	
	
	function iniciarRequisicao(){
		
	}
	
	function finalizarRequisicao(){
		 
	}
	
	return ComboCidade;
	
	
}());




$(function (){
	var comboEstado = new Busca.ComboEstado();
	comboEstado.iniciar();
	
	var comboCidade = new Busca.ComboCidade(comboEstado);
	comboCidade.iniciar();
	
});