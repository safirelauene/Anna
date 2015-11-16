package br.com.fatec.anna.api.entity;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */


public class Role extends ID_Anna {

	public static String TABELA = "ANNA_ROLE";
	public static String COL_ID = "ROL_ID";
	public static String COL_NOME = "ROL_NOME";
	private String nome;
	
	
	public Role(Long nextId, String string) {
		// = nextId;
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
