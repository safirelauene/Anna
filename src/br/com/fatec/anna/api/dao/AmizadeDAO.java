package br.com.fatec.anna.api.dao;

import java.util.List;

import br.com.fatec.anna.api.entity.Amizade;

/**
 * @author Safire Lauene
 * 
 * @version 1.0
 */

public interface AmizadeDAO {

	public Amizade save(Amizade a);
	public List<Amizade> findAll();
	public Amizade findByID(Long id);
	public void delete(Amizade a);
	
	
}
