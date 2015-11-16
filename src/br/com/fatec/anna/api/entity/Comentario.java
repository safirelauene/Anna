package br.com.fatec.anna.api.entity;

import java.util.Date;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */

public class Comentario extends ID_Anna{
	
	private Long idUsuario;
	private Long idPostagem;
	private Date data;
	private String conteudo;
	
	public static String TABELA = "ANNA_Comentario";
	public static String COL_ID = "COM_ID";
	public static String COL_IDUSUARIO = "COM_IDUSUARIO";
	public static String COL_IDPOST = "COM_IDPOST";
	public static String COL_DATA = "COM_DATA";
	public static String COL_CONTEUDO = "COM_CONTEUDO";
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdPost() {
		return idPostagem;
	}
	public void setIdPost(Long idPostagem) {
		this.idPostagem = idPostagem;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	
	
	
	
	
	
}
