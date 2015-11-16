package br.com.fatec.anna.api.entity;

import java.util.Date;

/**
 * @author Marcos Hideki
 * 
 * @version 1.0
 */

public class Post extends ID_Anna {
	// Variaveis de classe
	private String conteudo;	// Conteudo do Post
	private Date data;			// Data de cruação do Post
	private long idCriador;		// ID do criador do Post
	private int grupo;		// Se o Post pertence a um grupo ou nao [TRUE/FALSE]
	private long idGrupo;		// Pode ser NULL, caso nao pertenca a um grupo

	// Variaveis para uso do banco
	public static String TABELA = "ANNA_POST";
	public static String COL_ID = "POS_ID";
	public static String COL_CONTEUDO = "POS_CONTEUDO";
	public static String COL_IDCRIADOR = "POS_IDCRIADOR";
	public static String COL_GRUPO = "POS_GRUPO";
	public static String COL_IDGRUPO = "POS_IDGRUPO";
	public static String COL_DATA = "POS_DATA";


	// Getters and Setters

	// Conteudo
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String c) {
		this.conteudo = c;
	}

	// Data criacao
	public Date getData() {
		return data;
	}
	public void setData(Date d) {
		this.data = d;
	}

	// ID do Criador do Post
	public long getIdCriador() {
		return idCriador;
	}
	public void setIdCriador(long id) {
		this.idCriador = id;
	}

	// Boolean se pertence a um grupo ou nao
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int c) {
		this.grupo = c;
	}

	// Id do grupo que o Post pertenca
	public long getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(long id) {
		this.idGrupo = id;
	}


}