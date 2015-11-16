package br.com.fatec.anna.api.dao;

import java.util.List;

import br.com.fatec.anna.api.entity.*;

/**
 * @author Safire Lauene
 * 
 * @version 1.0
 */

public interface UsuarioDAO {
	//C
	public Usuario save(Usuario u);
	//R
	public List<Usuario> findAll();
	public Usuario findByID(Long id);
	//U
	public Usuario update(Usuario e);
	//D
	public void delete(Usuario e);
	
	public Usuario findByLogin(String u);
}
