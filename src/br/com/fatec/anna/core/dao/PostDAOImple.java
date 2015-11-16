package br.com.fatec.anna.core.dao;

/**
 * @author Marcos Hideki
 * 
 * @version 1.0
 */

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

import br.com.fatec.anna.api.dao.PostDAO;
import br.com.fatec.anna.api.entity.Post;
import br.com.fatec.anna.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
public class PostDAOImple implements PostDAO {

	// Implementacao de salvar
	public Post save(Post p) {
		// Inicializando as variaveis de query e connection
		Connection conn = null;
		PreparedStatement insert = null;

		try {
			// Estabelecendo conexao com o banco
			conn = ConfigDBMapper.getDefaultConnection();
			// Criando a QUERY
			insert = conn.prepareStatement("INSERT INTO ANNA_POST VALUES (?, ?, ?, ?, ?, ?)");

			// Gerando a ID para inserir no banco
			Long id = GeradorIdService.getNextId("ANNA_POST");
			
			// Inserindo os values no prepareStatement
			insert.setLong(1, id);
			insert.setString(2, p.getConteudo());

			Date dt = new Date(p.getData().getTime());
			
			insert.setDate(3, dt);
			insert.setLong(4, p.getIdCriador());
			insert.setInt(5, p.getGrupo());
			insert.setLong(6, p.getIdGrupo());

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
	public List<Post> findAll() {
		// Inicializando as variaveis de query, connection e a List
		Connection conn = null;
		PreparedStatement select = null;
		List<Post> result = new LinkedList<Post>();

		try {
			// Estabelencendo conexao com o banco
			conn = ConfigDBMapper.getDefaultConnection();
			// Criando a Query
			select = conn.prepareStatement("SELECT * FROM ANNA_POST");
			// ResultSet para o resultado e executando a query
			ResultSet rs = select.executeQuery();

			// Percorrendo os resultados e adicionando a lista
			while(rs.next()) result.add(criarPost(rs));

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
	public Post findById(long id) {
		// Inicializando as variáveis de query e connection
		Connection conn = null;
		PreparedStatement select = null;

		try {
			// Estabelecendo conexao com o banco de dados
			conn = ConfigDBMapper.getDefaultConnection();

			// Criando a Query
			select = conn.prepareStatement(
				"SELECT * FROM ANNA_POST WHERE POS_ID = ?");
			select.setLong(1, id);

			// ResultSet para o resultado
			ResultSet result = select.executeQuery();
			result.next();
			return criarPost(result);
		} catch (Exception ex) {
			// Caso haja exception, ele jogara uma Runtime Exception
			throw new RuntimeException(ex);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	// Implementacao do update
	public Post update(Post p) {
		// Inicializando as variaveis de query e connection
		Connection conn = null;
		PreparedStatement update = null;

		try {
			// Estabelecendo conexao com o banco de dados
			conn = ConfigDBMapper.getDefaultConnection();

			// Criando a query
			update = conn.prepareStatement(
				"UPDATE FROM ANNA_POST(POS_CONTEUDO) VALUES(?) WHERE POS_ID = ?");
			update.setString(1, p.getConteudo());
			update.setLong(2, p.getId());

			update.execute();
			return this.findById(p.getId());
		} catch (Exception ex) {
			// Jogando exception
			throw new RuntimeException(ex);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	// Implementacao do Delete
	public void delete(Post p) {
		// Inicializando as variaveis de query e connection
		Connection conn = null;
		PreparedStatement delete = null;

		try {
			// Estabelecendo conexao com o banco de dados
			conn = ConfigDBMapper.getDefaultConnection();

			// Criando a query
			delete = conn.prepareStatement(
				"DELETE FROM ANNA_POST WHERE POS_ID = ?");

			delete.setLong(1, p.getId());
			delete.execute();
		} catch (Exception ex) {
			// Jogando Exception
			throw new RuntimeException(ex);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	private static Post criarPost(ResultSet rs) throws SQLException, ParseException{
		Post p = new Post();
		p.setId(rs.getLong(Post.COL_ID));
		p.setConteudo(rs.getString(Post.COL_CONTEUDO));
		p.setIdCriador(rs.getLong(Post.COL_IDCRIADOR));
		p.setGrupo(rs.getInt(Post.COL_GRUPO));
		p.setIdGrupo(rs.getLong(Post.COL_IDGRUPO));
		String dataString = rs.getString(Post.COL_DATA);  
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);  
        Date data = (Date) df.parse(dataString); 
		p.setData(data);		
		return p;
	}
}