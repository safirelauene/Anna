package br.com.fatec.anna.api.dao;

import br.com.fatec.anna.api.entity.Post;

/**
 * @author Marcos Hideki
 * 
 * @version 1.0
 */

public interface PostDAO {

	// CRUD
	// Save Post
	public Post save(Post p);

	// Find By Id
	public Post findById(long id);

	// Update Post
	public Post update(Post p);

	// Delete Post
	public void delete(Post p);

}