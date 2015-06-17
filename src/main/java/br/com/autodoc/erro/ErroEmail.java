package br.com.autodoc.erro;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErroEmail {
	
	private int codigo;
	private String descricao;
	
	public ErroEmail() {
		
	}
	
	public ErroEmail(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
