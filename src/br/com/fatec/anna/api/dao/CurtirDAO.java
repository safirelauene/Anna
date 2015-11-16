package br.com.fatec.anna.api.dao;

import java.util.List;

import br.com.fatec.anna.api.entity.Curtir;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */

public interface CurtirDAO {
	
	//C
	public Curtir save(Curtir cr);
	//R
	public List<Curtir> findAll();
	public Curtir findByID(Long id);
	//U
	public Curtir update(Curtir cr);
	//D
	public void delete(Curtir cr);

}

