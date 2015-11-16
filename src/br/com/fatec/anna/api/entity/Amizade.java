package br.com.fatec.anna.api.entity;

/**
 * @author Safire Lauene
 * 
 * @version 1.0
 */

public class Amizade extends ID_Anna {
	
	private Long idAmizade;
	private Long idUsuario1;
	private Long idUsuario2;
	
	public static String TABELA = "ANNA_AMIZADE";
	public static String COL_ID = "AMI_ID";
	public static String COL_IDUSUARIO1 = "AMI_IDUSUARIO1";
	public static String COL_IDUSUARIO2 = "AMI_IDUSUARIO2";
	
	public Long getIdAmizade() {
		return idAmizade;
	}
	public void setIdAmizade(Long idAmizade) {
		this.idAmizade = idAmizade;
	}
	public Long getIdUsuario1() {
		return idUsuario1;
	}
	public void setIdUsuario1(Long idUsuario1) {
		this.idUsuario1 = idUsuario1;
	}
	public Long getIdUsuario2() {
		return idUsuario2;
	}
	public void setIdUsuario2(Long idUsuario2) {
		this.idUsuario2 = idUsuario2;
	}
		

}
