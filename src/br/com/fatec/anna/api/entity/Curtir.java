package br.com.fatec.anna.api.entity;

import java.util.Date;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */

public class Curtir extends ID_Anna {
	
	private Long idUsuario;
	private Long idPost;
	private Date data;
	
	public static String TABELA = "ANNA_CURTIR";
	public static String COL_ID = "CUR_ID";
	public static String COL_IDUSUARIO = "CUR_IDUSUARIO";
	public static String COL_IDPOST = "CUR_IDPOST";
	public static String COL_DATA = "CUR_DATA";
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdPost() {
		return idPost;
	}
	public void setIdPost(Long idPostagem) {
		this.idPost = idPostagem;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}	
}
