package br.com.fatec.anna.api.dao;

import java.util.List;

/**
 * @author Safire Lauene
 * 
 * @version 1.0
 */

import br.com.fatec.anna.api.entity.UsuarioGrupo;

public interface UsuarioGrupoDAO {
	
	//C
	public UsuarioGrupo save(UsuarioGrupo u);
	//R
	public List<UsuarioGrupo> findAll();
	public UsuarioGrupo findByID(Long idUsuario, Long idGrupo);
	//U
	public UsuarioGrupo update(UsuarioGrupo e);
	//D
	public void delete(UsuarioGrupo e);

}
