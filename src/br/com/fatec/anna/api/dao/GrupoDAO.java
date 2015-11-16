package br.com.fatec.anna.api.dao;

import java.util.List;

import br.com.fatec.anna.api.entity.Grupo;

/**
 * @author Marcos Hideki
 * 
 * @version 1.0
 */

public interface GrupoDAO {
	
	// CRUD
	// Save Grupo
	public Grupo save(Grupo g);

	// Find All Grupo
	public List<Grupo> findAll();

	// Find Grupo By Id
	public Grupo findById(long id);

	// Update Grupo
	public Grupo update(Grupo g);

	// Delete Grupo
	public void delete(Grupo g);

}