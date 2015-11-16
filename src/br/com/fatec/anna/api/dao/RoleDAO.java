package br.com.fatec.anna.api.dao;

import java.util.List;

import br.com.fatec.anna.api.entity.Role;
import br.com.fatec.anna.api.entity.Usuario;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */


public interface RoleDAO {
	
	//C
	public Role save(Role rl);
	//R
	public List<Role> findAll();
	public Role findByID(Long id);
	public Usuario findByLogin(String login);
	//U
	public Role update(Role rl);
	//D
	public void delete(Role rl);


}
