package br.com.fatec.anna.api.entity;

import java.util.Date;

/**
 * @author Marcos Hideki
 * 
 * @version 1.0
 */

public class Grupo extends ID_Anna {
	// Variaveis da classe
	private String nome;	// Nome
	private String desc;	// Descricao
	private Date cData;		// Data de Criação

	// Variaveis para uso do banco
	public static String TABELA = "ANNA_GRUPO";
	public static String COL_ID = "GRU_ID";
	public static String COL_NOME = "GRU_NOME";
	public static String COL_DESC = "GRU_DESCRICAO";
	public static String COL_DATA = "GRU_DATA";
	
	// Getters and Setters

	// Name
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// Descricao
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	// Data criacao
	public Date getcData() {
		return cData;
	}

	public void setcData(Date cData) {
		this.cData = cData;
	}

}