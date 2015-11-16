package br.com.fatec.anna.api.entity;

/**
 * @author Safire Lauene
 * 
 * @version 1.0
 */


public class Usuario extends ID_Anna{
	private String login;
	private String senha;
	private Role role;
	private Spec spec;
	
	
	public static String TABELA = "ANNA_Usuario";
	public static String COL_ID = "USU_ID";
	public static String COL_LOGIN = "USU_LOGIN";
	public static String COL_SENHA = "USU_SENHA";
	public static String COL_ROLE = "USU_ROLE";
	
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}

	public void setSenha(String s){
		this.senha = s;
	}
	
	
	public Spec getSpec(){
		return this.spec;
	}
	public void setSpec(Spec spec) {
		this.spec = spec;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
	
	
}
