package br.com.fatec.anna.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.anna.api.entity.Role;
import br.com.fatec.anna.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */


public class RoleDAOImple {
	
	public Role save(Role rl) {
		Connection c = null;
		PreparedStatement insert = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			insert = c.prepareStatement("insert into ANNA_Role"
					+ " values(?, ?);");
			Long id = GeradorIdService.getNextId("ANNA_Role");
			insert.setLong(1, id);
			insert.setString(2, rl.getNome());
			insert.execute();
			return this.findByID(id);
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}
	

	public List<Role> findAll() {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from ANNA_Role;");
			ResultSet rs = select.executeQuery();
			return criarRoles(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	public Role findByID(Long id) {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from ANNA_Role where ID = ?;");
			select.setLong(1, id);
			ResultSet rs = select.executeQuery();
			//Bolo de cenoura
			rs.next();
			return criarRole(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	public Role update(Role rl) {
		Connection c = null;
		PreparedStatement update = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			update = c.prepareStatement("UPDATE " + Role.TABELA + " SET " +
				Role.COL_ID + " = " + rl.getId() + " ," + 
				Role.COL_NOME + " = " + rl.getNome() +
				"WHERE COL_ID = " + rl.getId() + ";");
			update.execute();
			return this.findByID(rl.getId());
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	public void delete(Role rl) {
		Connection c = null;
		PreparedStatement delete = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			delete = c.prepareStatement("DELETE FROM " + Role.TABELA + 
				"WHERE ID = " + rl.getId() + ";");
			delete.execute();
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}
	private static List<Role> criarRoles (ResultSet rs) throws SQLException, ParseException{
		List<Role> lte = new LinkedList<Role>();
		while(rs.next()){
			Role te = new Role(null, null);
			te = criarRole(rs);
			lte.add(te);
		}
		return lte;
	}
	
	private static Role criarRole(ResultSet rs) throws SQLException, ParseException{
		Role rl = new Role(null, null);
		rl.setId(rs.getLong(Role.COL_ID));
		rl.setNome(rs.getString(Role.COL_NOME));
		return rl;
	}

}
