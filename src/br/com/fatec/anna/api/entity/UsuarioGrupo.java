package br.com.fatec.anna.api.entity;

import java.util.Date;

/**
 * @author Safire Lauene
 * 
 * @version 1.0
 */

public class UsuarioGrupo {
	
	private long idUsuario;
	private long idGrupo;
	private Date dataEntrada;
	
	public static String TABELA = "ANNA_USUARIO_GRUPO";
	public static String COL_IDUSUARIO = "USUARIO_GRUPO_IDUSUARIO";
	public static String COL_IDGRUPO = "USUARIO_GRUPO_IDGRUPO";
	public static String COL_DATAENTRADA = "USUARIO_GRUPO_DATA";
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	

}
