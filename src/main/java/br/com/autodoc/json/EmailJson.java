package br.com.autodoc.json;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.autodoc.entity.Email;
import br.com.autodoc.erro.ErroEmail;
import br.com.autodoc.fila.Fila;

@Path("email")
public class EmailJson {
 
	//@Path("envio")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ErroEmail addEmail(Email email) {
		
		Fila fila = new Fila("Email");
		fila.add(email);
		
		ErroEmail erro = new ErroEmail(1,"Email Adicionado a Fila");
		return erro;
	}
	
	//@Path("teste")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Email getEmailTeste(){
		
		Email email = new Email();
		email.setTo("dcaetano@autodoc.com.br");
		email.setFrom("sistemas@autodoc.com.br");
		email.setAssunto("Teste");
		email.setCorpo("<html><head></head><body>Teste de texto</body></html>");
		
		return email;
	}
 
}