package br.com.fatec.anna.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.anna.core.service.GeradorIdService;
import br.com.fatec.anna.api.dao.ComentarioDAO;
import br.com.fatec.anna.api.entity.Comentario;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */

public class ComentarioDAOImple implements ComentarioDAO {

	public Comentario save(Comentario cmm) {
		Connection c = null;
		PreparedStatement insert = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			insert = c.prepareStatement("insert into ANNA_COMENTARIO"
					+ " values(?, ?, ?, ?, ?);");
			Long id = GeradorIdService.getNextId("ANNA_COMENTARIO");
			insert.setLong(1, id);
			insert.setLong(2, cmm.getIdUsuario());
			insert.setLong(3, cmm.getIdPost());
			
			Date dt = new Date(cmm.getData().getTime());
			
			insert.setDate(4, dt);
			insert.setString(5, cmm.getConteudo());
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
	

	public List<Comentario> findAll() {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from ANNA_COMENTARIO;");
			ResultSet rs = select.executeQuery();
			return criarComentarios(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	public Comentario findByID(Long id) {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from ANNA_Comentario where ID = ?;");
			select.setLong(1, id);
			ResultSet rs = select.executeQuery();
			//Bolo de cenoura
			rs.next();
			return criarComentario(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	public Comentario update(Comentario cmm) {
		Connection c = null;
		PreparedStatement update = null;
		try{
			Date dt = new Date(cmm.getData().getTime());
			c = ConfigDBMapper.getDefaultConnection();
			update = c.prepareStatement("UPDATE " + cmm.TABELA + " SET " +
				cmm.COL_ID + " = " + cmm.getId() + " ," + 
				cmm.COL_IDUSUARIO + " = " + cmm.getIdUsuario()+ " ," +
				cmm.COL_IDPOST + " = " + cmm.getIdPost() +" ," + 
				cmm.COL_DATA + " = " + dt + ", " +
				cmm.COL_CONTEUDO + " = " + cmm.getConteudo() +
				"WHERE COL_ID = " + cmm.getId() + ";");
			update.execute();
			return this.findByID(cmm.getId());
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	public void delete(Comentario e) {
		Connection c = null;
		PreparedStatement delete = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			delete = c.prepareStatement("DELETE FROM " + e.TABELA + 
				"WHERE ID = " + e.getId() + ";");
			delete.execute();
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}
	private static List<Comentario> criarComentarios (ResultSet rs) throws SQLException, ParseException{
		List<Comentario> lte = new LinkedList<Comentario>();
		while(rs.next()){
			Comentario te = new Comentario();
			te = criarComentario(rs);
			lte.add(te);
		}
		return lte;
	}
	
	private static Comentario criarComentario(ResultSet rs) throws SQLException, ParseException{
		Comentario cmm = new Comentario();
		cmm.setId(rs.getLong(Comentario.COL_ID));
		cmm.setIdUsuario(rs.getLong(Comentario.COL_IDUSUARIO));
		cmm.setIdPost(rs.getLong(Comentario.COL_IDPOST));
		String dataString = rs.getString(Comentario.COL_DATA);  
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);  
        Date data = (Date) df.parse(dataString); 
		cmm.setData(data);		
		cmm.setConteudo(rs.getString(Comentario.COL_CONTEUDO));
		return cmm;
	}
		
}
	