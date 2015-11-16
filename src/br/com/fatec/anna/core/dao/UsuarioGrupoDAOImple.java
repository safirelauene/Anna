package br.com.fatec.anna.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.anna.api.dao.UsuarioGrupoDAO;
import br.com.fatec.anna.api.entity.Spec;
import br.com.fatec.anna.api.entity.Usuario;
import br.com.fatec.anna.api.entity.UsuarioGrupo;
import br.com.fatec.anna.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

/**
 * @author Safire Lauene
 * 
 * @version 1.0
 */

public class UsuarioGrupoDAOImple implements UsuarioGrupoDAO{

	@Override
	public UsuarioGrupo save(UsuarioGrupo u) {
		Connection c = null;
		PreparedStatement insert = null;
		

		try{
			c = ConfigDBMapper.getDefaultConnection();
			insert = c.prepareStatement("insert into "+UsuarioGrupo.TABELA+ " values(?, ?, ?);");
			
			insert.setLong(1, u.getIdUsuario());
			insert.setLong(2, u.getIdGrupo());
			Date d = new Date(u.getDataEntrada().getTime());
			insert.setDate(3, d);
			insert.execute();
			
			return this.findByID(u.getIdUsuario(),u.getIdGrupo());
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}

	}

	@Override
	public List<UsuarioGrupo> findAll() {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from "+UsuarioGrupo.TABELA+";");
			ResultSet rs = select.executeQuery();
			return criarUsuariosGrupos(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	@Override
	public UsuarioGrupo findByID(Long idU, Long idG) {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from "+UsuarioGrupo.TABELA+" where "+UsuarioGrupo.COL_IDUSUARIO+" = ? and "+UsuarioGrupo.COL_IDGRUPO+" = ? ;");
			select.setLong(1, idU);
			select.setLong(2,idG);
			ResultSet rs = select.executeQuery();
			
			rs.next();
			return criarUsuarioGrupo(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	@Override
	public UsuarioGrupo update(UsuarioGrupo e) {
		Connection c = null;
		PreparedStatement update = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			update = c.prepareStatement("UPDATE " + UsuarioGrupo.TABELA + " SET " + UsuarioGrupo.COL_IDUSUARIO+ e.getIdUsuario() + " ," + 
				UsuarioGrupo.COL_IDGRUPO + " = " + e.getIdGrupo()+ " ," +
				UsuarioGrupo.COL_DATAENTRADA + " = " + e.getDataEntrada()+ 
				"WHERE "+ UsuarioGrupo.COL_IDUSUARIO+" = " + e.getIdUsuario() + " and "+UsuarioGrupo.COL_IDGRUPO+" = "+e.getIdGrupo()+";");
			update.execute();
			return this.findByID(e.getIdUsuario(), e.getIdGrupo());
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	@Override
	public void delete(UsuarioGrupo e) {
		Connection c = null;
		PreparedStatement delete = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			delete = c.prepareStatement("DELETE FROM " + UsuarioGrupo.TABELA + 
				"WHERE "+UsuarioGrupo.COL_IDUSUARIO+" = " + e.getIdUsuario() +" and "+UsuarioGrupo.COL_IDGRUPO+" = "+e.getIdGrupo()+ ";");
			delete.execute();
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
		
	}
	
	private static List<UsuarioGrupo> criarUsuariosGrupos (ResultSet rs) throws SQLException{
		List<UsuarioGrupo> ugs = new LinkedList<UsuarioGrupo>();
		while(rs.next()){
			UsuarioGrupo e = new UsuarioGrupo();
			e = criarUsuarioGrupo(rs);
			ugs.add(e);
		}
		return ugs;
	}
	
	private static UsuarioGrupo criarUsuarioGrupo(ResultSet rs) throws SQLException{
		UsuarioGrupo ug = new UsuarioGrupo();
		ug.setIdUsuario(rs.getLong(UsuarioGrupo.COL_IDUSUARIO));
		ug.setIdGrupo(rs.getLong(UsuarioGrupo.COL_IDGRUPO));
		ug.setDataEntrada(rs.getDate(UsuarioGrupo.COL_DATAENTRADA));
		
		return ug;
	}
	

}
