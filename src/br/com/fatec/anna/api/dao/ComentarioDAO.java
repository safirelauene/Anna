package br.com.fatec.anna.api.dao;

import java.util.List;

import br.com.fatec.anna.api.entity.Comentario;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */

public interface ComentarioDAO {
	//C
	public Comentario save(Comentario c);
	//R
	public List<Comentario> findAll();
	public Comentario findByID(Long id);
	//U
	public Comentario update(Comentario c);
	//D
	public void delete(Comentario e);

}
