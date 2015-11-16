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

import br.com.fatec.anna.api.dao.CurtirDAO;
import br.com.fatec.anna.api.entity.Comentario;
import br.com.fatec.anna.api.entity.Curtir;
import br.com.fatec.anna.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */


public class CurtirDAOImple implements CurtirDAO {

	@Override
	public Curtir save(Curtir cr) {
		Connection c = null;
		PreparedStatement insert = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			insert = c.prepareStatement("insert into ANNA_CURTIR"
					+ " values(?, ?, ?, ?, ?);");
			Long id = GeradorIdService.getNextId("ANNA_CURTIR");
			insert.setLong(1, id);
			insert.setLong(2, cr.getIdUsuario());
			insert.setLong(3, cr.getIdPost());
			Date dt = new Date(cr.getData().getTime());
			insert.setDate(4, dt);
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
	public List<Curtir> findAll() {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from ANNA_CURTIR;");
			ResultSet rs = select.executeQuery();
			return criarCurtidas(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	@Override
	public Curtir findByID(Long id) {
		Connection c = null;
		PreparedStatement select = null;
		try{
			c = ConfigDBMapper.getDefaultConnection();
			select = c.prepareStatement("Select * from ANNA_CURTIR where ID = ?;");
			select.setLong(1, id);
			ResultSet rs = select.executeQuery();
			//Bolo de cenoura
			rs.next();
			return criarCurtir(rs);
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	@Override
	public Curtir update(Curtir cu) {
		Connection c = null;
		PreparedStatement update = null;
		try{
			Date dt = new Date(cu.getData().getTime());
			c = ConfigDBMapper.getDefaultConnection();
			update = c.prepareStatement("UPDATE " + cu.TABELA + " SET " +
				cu.COL_ID + " = " + cu.getId() + " ," + 
				cu.COL_IDUSUARIO + " = " + cu.getIdUsuario()+ " ," +
				cu.COL_IDPOST + " = " + cu.getIdPost() +" ," + 
				cu.COL_DATA + " = " + dt + ", " +
				"WHERE COL_ID = " + cu.getId() + ";");
			update.execute();
			return this.findByID(cu.getId());
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			DbUtils.closeQuietly(c);
		}
	}

	@Override
	public void delete(Curtir e) {
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
	
	private static List<Curtir> criarCurtidas (ResultSet rs) throws SQLException, ParseException{
		List<Curtir> ltc = new LinkedList<Curtir>();
		while(rs.next()){
			Curtir tc = new Curtir();
			tc = criarCurtir(rs);
			ltc.add(tc);
		}
		return ltc;
	}
	
	private static Curtir criarCurtir(ResultSet rs) throws SQLException, ParseException{
		Curtir crt = new Curtir();
		crt.setId(rs.getLong("COL_ID"));
		crt.setIdUsuario(rs.getLong(Curtir.COL_IDUSUARIO));
		crt.setIdPost(rs.getLong(Curtir.COL_IDPOST));
		String dataString = rs.getString(Curtir.COL_DATA);  
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);  
        Date data = (Date) df.parse(dataString); 
		crt.setData(data);		
		return crt;
	}

}
