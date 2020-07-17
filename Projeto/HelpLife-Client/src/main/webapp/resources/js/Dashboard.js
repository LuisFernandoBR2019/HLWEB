function contaCampanhaPizza() {
debugger;
	jQuery.ajax({
		url : "../api/v1/helplife/dashboard/contadorCampanha",
		type : "GET",
		contentType : "application/json",
		dataType : "json",
		error : function() {
			debugger;
		},
		success : function(dados) {
//			criaGraficoPizza(dados);
			contaSolicitacaoPizza(dados);
		}
	});
}

function contaSolicitacaoPizza(contador) {
	debugger;
	var contadorCampanha = contador;
	jQuery.ajax({
		url : "../api/v1/helplife/dashboard/contadorSolicitacao",
		type : "GET",
		contentType : "application/json",
		dataType : "json",
		error : function() {
			debugger;
		},
		success : function(dados) {
			let contadorSolicitacao = dados;
			criaGraficoPizza(contadorCampanha,contadorSolicitacao);
		}
	});
}

function criaGraficoPizza(contadorCampanha,contadorSolicitacao) {
	debugger;
	var Campanha, Solicitacao;

	Campanha = contadorCampanha;
	Solicitacao = contadorSolicitacao;

	var ctx = document.getElementById('myChart').getContext('2d');

	var myChart = new Chart(ctx, {
		type : 'pie',
		data : {
			labels : [ 'Campanhas', 'Solicitacoes' ],
			datasets : [ {
				label : '# of Votes',
				data : [ Campanha, Solicitacao],
				backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)'],
				borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)'],
				borderWidth : 1
			} ]
		},
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ]
			}
		}
	});
}

function recebeDadosBar(){
	debugger;
	jQuery.ajax({
		url : "../api/v1/helplife/dashboard/alimentaGraficoTabela",
		type : "GET",
		contentType : "application/json",
		dataType : "json",
		error : function() {
			debugger;
		},
		success : function(dados) {
			debugger;
			let dadosBars = dados;
			criaBar(dadosBars);
				
		}
	});
}

function criaBar (dadosBars){
	debugger;
	let mapaBars = [];
	mapaBars = Object.entries(dadosBars);
	let dataTipoSangue = [0,0,0,0,0,0,0,0];
	let j = 0;
	for(let i = 0; i < mapaBars.length; i++){
		
		if (mapaBars[i][j] === "A+"){
			dataTipoSangue[0] =mapaBars[i][++j];
			j = 0;
		}
		
		if (mapaBars[i][j] === "A-"){
			dataTipoSangue[1] =mapaBars[i][++j];
			j = 0;
		}
		
		if (mapaBars[i][j] === "B+"){
			dataTipoSangue[2] =mapaBars[i][++j];
			j = 0;
		}
		
		if (mapaBars[i][j] === "B-"){
			dataTipoSangue[3] =mapaBars[i][++j];
			j = 0;
		}

		if (mapaBars[i][j] === "O+"){
			dataTipoSangue[4] =mapaBars[i][++j];
			j = 0;
		}

		if (mapaBars[i][j] === "O-"){
			dataTipoSangue[5] =mapaBars[i][++j];
			j = 0;
		}

		if (mapaBars[i][j] === "AB+"){
			dataTipoSangue[6] =mapaBars[i][++j];
			j = 0;
		}

		if (mapaBars[i][j] === "AB-"){
			dataTipoSangue[7] =mapaBars[i][++j];
			j = 0;
		}
		
	}
	
	var ctx = document.getElementById('myBars');
	var myBarChart = new Chart(ctx, {
	    type: 'bar',
	    data:  {
			labels : [ 'Tipos Sanguineos','A+', 'A-', 'B+' ,'B-', 'O+', 'O-', 'AB+', 'AB-'],
			datasets : [ {
				label : '# Limpar Grafico',
				data : [0,dataTipoSangue[0],dataTipoSangue[1],dataTipoSangue[2],dataTipoSangue[3],dataTipoSangue[4],
				dataTipoSangue[5],dataTipoSangue[6],dataTipoSangue[7]],
				backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)' ],
				borderColor : [ 'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)', 'rgba(75, 192, 192, 1)', 'rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)', 'rgba(75, 192, 192, 1)','rgba(255, 99, 132, 1)',
					'rgba(54, 162, 235, 1)' ],
				borderWidth : 1
			} ]
		}
	});
}