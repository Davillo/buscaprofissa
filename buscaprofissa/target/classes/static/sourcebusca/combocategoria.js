var BuscaProfissa = BuscaProfissa || {};

BuscaProfissa.ComboArea = (function() {
	
	function ComboArea() {
		this.combo = $('#area');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboArea.prototype.iniciar = function() {
		this.combo.on('change', onAreaAlterada.bind(this));
	}
	
	function onAreaAlterada() {
		this.emitter.trigger('alterada', this.combo.val());
	}
	
	return ComboArea;
	
}());

BuscaProfissa.ComboCategoria = (function() {
	
	function ComboCategoria(comboArea) {
		this.comboArea = comboArea;
		this.combo = $('#categoria');
		this.inputHiddenCategoriaSelecionada = $('#inputHiddenCategoriaSelecionada');
	}
	
	ComboCategoria.prototype.iniciar = function() {
		reset.call(this);
		this.comboArea.on('alterada', onAreaAlterada.bind(this));
		var idArea = this.comboArea.combo.val();
		inicializarCategorias.call(this, idArea);
	}
	
	function onAreaAlterada(evento, idArea) {
		this.inputHiddenCategoriaSelecionada.val('');
		inicializarCategorias.call(this, idArea);
	}
	
	function inicializarCategorias(idArea) {
		if (idArea) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'area': idArea }, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarCategoriaFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarCategoriaFinalizado(categorias) {
		var options = [];
		categorias.forEach(function(categoria) {
			options.push('<option value="' + categoria.id + '">' + categoria.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
		var idCategoriaSelecionada = this.inputHiddenCategoriaSelecionada.val();
		if (idCategoriaSelecionada) {
			this.combo.val(idCategoriaSelecionada);
		}
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione a Categoria</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function iniciarRequisicao() {
		reset.call(this);
	}
	
	function finalizarRequisicao() {
	}
	
	return ComboCategoria;
	
}());

$(function() {
	
	var comboArea = new BuscaProfissa.ComboArea();
	comboArea.iniciar();
	
	var comboCategoria = new BuscaProfissa.ComboCategoria(comboArea);
	comboCategoria.iniciar();
	
});

