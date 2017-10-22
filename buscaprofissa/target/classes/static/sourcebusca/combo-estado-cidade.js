var Busca = Busca || {};


Busca.ComboEstado=  (function (){
	
	
	function ComboEstado(){
		this.combo = $('#estado');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboEstado.prototype.iniciar = function (){
		this.combo.on('change', onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado(){
		this.emitter.trigger('alterado',this.combo.val());
	}
	
	return ComboEstado;
}());


Busca.ComboCidade=  (function (){
	
	
	function ComboCidade(comboEstado){
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
	}
	
	ComboCidade.prototype.iniciar = function (){
		this.comboEstado.on('alterado',onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado(evento, id){
		if(id){
			
		
		var resposta = $.ajax({
			url: '/buscaprofissa/cidades',
			method: 'GET',
			contentType: 'application/json',
			data: {'estado': id },
			beforeSend: iniciarRequisicao.bind(this),
			complete: finalizarRequisicao.bind(this)
		});
		
			resposta.done(onBuscarCidadesFinalizado.bind(this));
		}else{
			reset.call(this);
		}
		
	}
	
	
	function onBuscarCidadesFinalizado(cidades){
		var options = [];
		cidades.forEach(function(cidade){
			options.push('<option value="' + cidade.id + '">' + cidade.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
	}
	
	function reset(){
		this.combo.html('<option value="">Selecione a Cidade</option>');
		this.combo.val('');
		this.combo.attr('disabled','disabled');
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