package br.com.fatec.anna.api.entity;
import java.util.Date;

/**
 * @author Safire Lauene
 * 
 * @version 1.0
 */


public class Spec {
	
	private String nome;
	private String genero;
	private String frase;
	private String cidade;
	private Date data_nasc;
	
	public static String TABLE = "ANNA_Usuario";
	public static String COL_NOME = "USU_NOME";
	public static String COL_GENERO = "USU_GENERO";
	public static String COL_FRASE = "USU_FRASE";
	public static String COL_CIDADE = "USU_CIDADE";
	public static String COL_D_NASC = "USU_D_NASC";
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getFrase() {
		return frase;
	}
	public void setFrase(String frase) {
		this.frase = frase;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Date getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}
	
	

}
