package br.com.fatec.anna.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import br.com.fatec.anna.api.dao.UsuarioDAO;
import br.com.fatec.anna.api.entity.Spec;
import br.com.fatec.anna.api.entity.Usuario;
import br.com.fatec.anna.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;

import org.apache.commons.dbutils.DbUtils;

/**
 * @author Safire Lauene
 * 
 * @version 1.0
 */

public class UsuarioDAOImple implements UsuarioDAO {
	
	@Override
	public Usuario save(Usuario u) {

		Connection c = null;
		PreparedStatement insert = null;
		

		try{
			c = ConfigDBMapper.getDefaultConnection();
			insert = c.prepareStatement("insert into ANNA_USUARIO"
					+ " values(?, ?, ?, ?, ?, ?, ?, ?);");
			Long id = GeradorIdService.getNextId("ANNA_USUARIO");
			insert.setLong(1, id);
			insert.setString(2, u.getLogin());
			insert.setString(3, u.getSenha());
			insert.setString(4, u.getSpec().getNome());
			insert.setString(5, u.getSpec().getGenero());
			insert.setString(5, u.getSpec().getFrase());
			insert.setString(6, u.getSpec().getCidade());
			Date d = new Date(u.getSpec().getData_nasc().getTime());
			insert.setDate(7, d);
			
			
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

	@Override
	public List<Usuario> findAll() {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from ANNA_USUARIO;");
			ResultSet rs = select.executeQuery();
			return criarUsuarios(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	@Override
	public Usuario findByID(Long id) {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from ANNA_USUARIO where COL_ID = ?;");
			select.setLong(1, id);
			ResultSet rs = select.executeQuery();
			
			rs.next();
			return criarUsuario(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}
	
	public Usuario findByLogin(String u) {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from ANNA_USUARIO where COL_LOGIN = ?;");
			select.setString(1, u);
			ResultSet rs = select.executeQuery();
			
			rs.next();
			return criarUsuario(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	@Override
	public Usuario update(Usuario e) {
		Connection c = null;
		PreparedStatement update = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			update = c.prepareStatement("UPDATE " + Usuario.TABELA + " SET " +
				"USU_ID = " + e.getId() + " ," + 
				Usuario.COL_ID + " = " + e.getLogin()+ " ," +
				Usuario.COL_SENHA + " = " + e.getSenha()+ " ," +
				Spec.COL_NOME + " = " + e.getSpec().getNome()+ " ," +
				Spec.COL_GENERO + " = " + e.getSpec().getGenero()+ " ," +
				Spec.COL_FRASE + " = " + e.getSpec().getFrase()+ " ," +
				Spec.COL_CIDADE + " = " + e.getSpec().getCidade()+ " ," +
				Spec.COL_D_NASC + " = " + e.getSpec().getData_nasc()+ " ," +
				"WHERE USU_ID = " + e.getId() + ";");
			update.execute();
			return this.findByID(e.getId());
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	@Override
	public void delete(Usuario e) {
		Connection c = null;
		PreparedStatement delete = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			delete = c.prepareStatement("DELETE FROM " + Spec.TABLE + 
				"WHERE USU_ID = " + e.getId() + ";");
			delete.execute();
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
		
	}
	
	private static List<Usuario> criarUsuarios (ResultSet rs) throws SQLException{
		List<Usuario> le = new LinkedList<Usuario>();
		while(rs.next()){
			Usuario e = new Usuario();
			e = criarUsuario(rs);
			le.add(e);
		}
		return le;
	}
	
	private static Usuario criarUsuario(ResultSet rs) throws SQLException{
		Usuario u = new Usuario();
		u.setId(rs.getLong(Usuario.COL_ID));
		u.setLogin(rs.getString(Usuario.COL_LOGIN));
		u.setSenha(rs.getString(Usuario.COL_SENHA));
		Spec spec = new Spec();
		spec.setNome(rs.getString(Spec.COL_NOME));
		spec.setGenero(rs.getString(Spec.COL_GENERO));
		spec.setFrase(rs.getString(Spec.COL_FRASE));
		spec.setCidade(rs.getString(Spec.COL_CIDADE));
		spec.setData_nasc(rs.getDate(Spec.COL_D_NASC));
		
		u.setSpec(spec);
		
		return u;
	}


}
