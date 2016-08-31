$(window).load(function() {
	$(".loader").delay(300).fadeOut();
	$(".animationload").delay(600).fadeOut("slow");
});
var conctionSuccess = "<div class='alert alert-success' role='alert'><b>Conexão com o concentrador estabelecida com sucesso.</b></div>";
var conctionFail = "<div class='alert alert-danger' role='alert'><b>Falha ao tentar estabelecer a conexão com o concentrador.</b></div>";
var messageFail = "<div class='alert alert-danger' role='alert'><b>Ocorreu uma falha de conexão com o concentrador ou a mensagem enviada está incorreta.</b></div>";
var messageSuccess = "<div class='alert alert-success' role='alert'><b>Mensagem enviada com sucesso.</b></div>";
var conctionUrlFail = "<div class='alert alert-warning' role='alert'><b>Favor verificar o endereço de conexão com o concentrador.</b></div>";
var $table = $('#table');
var $button = $('#button');

var books =
[
	{
		"title": "Java How To Program",
		"author": "Deitel & Deitel",
		"year": 2007
	},
	{
		"title": "Patterns Of Enterprise Application Architecture",
		"author": "Martin Fowler",
		"year": 2002
	},
	{
		"title": "Head First Design Patterns",
		"author": "Elisabeth Freeman",
		"year": 2004
	},
	{
		"title": "Internet & World Wide Web How To Program",
		"author": "Deitel & Deitel",
		"year": 2007
	}
]

var json_str = JSON.stringify(books);

//formulário de envio
$("#postForm").submit(function(event) {

	$(".loader").delay(600).fadeIn();
	$(".animationload").delay(300).fadeIn("slow");

	var posting = $.ajax({
		url: $("input#serverUrl").val(),
		type: "POST",
		crossDomain: true,
		data: {
			configPath: $("input#configFile").val(),
			books: json_str
		}
	});

	posting.done( function( dataResult ) {
		$("#postResult").empty().append( messageSuccess );
		$("textarea#msgResposta").val(posting.responseText);
		$table.bootstrapTable('load',JSON.parse(posting.responseText));
		closeAlert();
	});
	posting.fail( function( xhr, status ) {
		$("#postResult").empty().append( messageFail );
		closeAlert();
	});
	posting.success( function( xhr, status ) {
		$("#postResult").empty().append( messageSuccess );
		closeAlert();
	});
	closeLoader();
	return false;
});

$(function () {
	$table.bootstrapTable({
		data: books
	});
});

$button.click(function () {
	$table.bootstrapTable('load', books);
});

function closeAlert(){
	$(".alert").fadeTo(4000, 1000).slideUp(1000, function(){
		$(".alert").alert('close');
	});
}

function closeLoader(){
	$(".loader").delay(550).fadeOut();
	$(".animationload").delay(600).fadeOut("slow");
}
