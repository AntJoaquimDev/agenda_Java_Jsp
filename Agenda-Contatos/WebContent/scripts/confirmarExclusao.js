/**
 * Excluir o contato
*
 */

function confirmar(id){
	let resposta = confirm("Confirma a exlusão deste contato?" )
	
	if(resposta === true){
	//alert(id)	
	window.location.href ="deletar?id="+ id;
	
	
	}
}