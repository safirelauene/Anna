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

import br.com.fatec.anna.api.dao.GrupoDAO;
import br.com.fatec.anna.api.entity.Grupo;
import br.com.fatec.anna.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

/**
 * @author Marcos Hideki
 * 
 * @version 1.0
 */

public class GrupoDAOImple implements GrupoDAO {

	// Implementacao de salvar
	public Grupo save(Grupo g) {
		// Inicializando as variaveis de query e connection
		Connection conn = null;
		PreparedStatement insert = null;

		try {
			// Estabelecendo conexao com o banco
			conn = ConfigDBMapper.getDefaultConnection();
			// Criando a QUERY
			insert = conn.prepareStatement("INSERT INTO ANNA_GRUPO VALUES (?, ?, ?, ?)");

			// Gerando a ID para inserir no banco
			Long id = GeradorIdService.getNextId("ANNA_GRUPO");
			
			// Inserindo os values no prepareStatement
			insert.setLong(1, id);
			insert.setString(2, g.getNome());
			insert.setString(3, g.getDesc());

			// Convertendo a data da Utils para SQL
			Date dt = new Date(g.getcData().getTime());
			insert.setDate(4, dt);

			// Executando a Query
			insert.execute();

			return this.findById(id);
		} catch (Exception ex) {
			// Caso haja exception ele jogara uma Runtime Exception
			throw new RuntimeException(ex);
		} finally {
			// Apos tudo o mesmo ira encerrar a conexao com o banco
			DbUtils.closeQuietly(conn);
		}

	}

	// Implementacao do findAll
	public List<Grupo> findAll() {
		// Inicializando as variaveis de query, connection e a List
		Connection conn = null;
		PreparedStatement select = null;
		List<Grupo> result = new LinkedList<Grupo>();

		try {
			// Estabelencendo conexao com o banco
			conn = ConfigDBMapper.getDefaultConnection();
			// Criando a Query
			select = conn.prepareStatement("SELECT * FROM ANNA_GRUPO");
			// ResultSet para o resultado e executando a query
			ResultSet rs = select.executeQuery();

			// Percorrendo os resultados e adicionando a lista
			while(rs.next()) result.add(criarGrupo(rs));

			return result;
		} catch (Exception ex) {
			// Caso haja exception ele jogara uma Runtime Exception
			throw new RuntimeException(ex);
		} finally {
			// Encerrando a conexao com o banco de dados
			DbUtils.closeQuietly(conn);
		}

	}

	// Implementacao do findById
	public Grupo findById(long id) {
		// Inicializando as variáveis de query e connection
		Connection conn = null;
		PreparedStatement select = null;

		try {
			// Estabelecendo conexao com o banco de dados
			conn = ConfigDBMapper.getDefaultConnection();

			// Criando a Query
			select = conn.prepareStatement(
				"SELECT * FROM ANNA_GRUPO WHERE GRU_ID = ?");
			select.setLong(1, id);

			// ResultSet para o resultado
			ResultSet result = select.executeQuery();
			result.next();
			return criarGrupo(result);
		} catch (Exception ex) {
			// Caso haja exception, ele jogara uma Runtime Exception
			throw new RuntimeException(ex);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	// Implementacao do update
	public Grupo update(Grupo g) {
		// Inicializando as variaveis de query e connection
		Connection conn = null;
		PreparedStatement update = null;

		try {
			// Estabelecendo conexao com o banco de dados
			conn = ConfigDBMapper.getDefaultConnection();

			// Criando a query
			update = conn.prepareStatement(
				"UPDATE FROM ANNA_GRUPO(GRU_NOME, GRU_DESCRICAO) VALUES(?, ?) WHERE GRU_ID = ?");
			update.setString(1, g.getNome());
			update.setString(2, g.getDesc());
			update.setLong(3, g.getId());

			update.execute();
			return this.findById(g.getId());
		} catch (Exception ex) {
			// Jogando exception
			throw new RuntimeException(ex);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	// Implementacao do Delete
	public void delete(Grupo g) {
		// Inicializando as variaveis de query e connection
		Connection conn = null;
		PreparedStatement delete = null;

		try {
			// Estabelecendo conexao com o banco de dados
			conn = ConfigDBMapper.getDefaultConnection();

			// Criando a query
			delete = conn.prepareStatement(
				"DELETE FROM ANNA_GRUPO WHERE GRU_ID = ?");

			delete.setLong(1, g.getId());
			delete.execute();
		} catch (Exception ex) {
			// Jogando Exception
			throw new RuntimeException(ex);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	private static Grupo criarGrupo(ResultSet rs) throws SQLException, ParseException{
		Grupo g = new Grupo();
		g.setId(rs.getLong(Grupo.COL_ID));
		g.setNome(rs.getString(Grupo.COL_NOME));
		g.setDesc(rs.getString(Grupo.COL_DESC));
		String dataString = rs.getString(Grupo.COL_DATA);  
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);  
        Date data = (Date) df.parse(dataString); 
		g.setcData(data);		
		return g;
	}
}