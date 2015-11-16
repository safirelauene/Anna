package br.com.fatec.anna.core.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Carlos
 *
 * @version 1.0.1
 */
public class GeradorIdService {

	private static GeradorIdService instance;
	private static Connection connection;
	private static Long idSequence = 1L;

	private GeradorIdService() {
	}

	/**
	 * @return inst�ncia de GeradorIdService
	 */
	public static GeradorIdService getInstance() {
		if (instance == null) {
			instance = new GeradorIdService();
			connection = br.com.spektro.minispring.core.dbmapper.ConfigDBMapper.getDefaultConnection();
		}
		return instance;
	}

	/**
	 * @param tableName
	 * @return novo ID ainda n�o utilizado
	 */
	// Agora iremos passar como argumento o nome da tabela alvo
	public synchronized static Long getNextId(String tableName) {
		try {
			// Iremos considerar essa tabela no SQL que ir� retornar o NEXT_ID
			PreparedStatement query = connection
					.prepareStatement("SELECT NEXT_ID FROM GERADOR_IDS WHERE TABLE_NAME = ?;");
			query.setString(1, tableName); // N�o se esque�a de fazer o set da
											// 'tableName'
			ResultSet resultNextId = query.executeQuery();
			resultNextId.next();
			idSequence = resultNextId.getLong("NEXT_ID");
			// Agora iremos atualizar o valor do NEXT_ID na Tabela, tamb�m
			// utilizando o tableName
			PreparedStatement updateID = connection
					.prepareStatement("UPDATE GERADOR_IDS SET NEXT_ID = ? WHERE TABLE_NAME = ?;");
			updateID.setLong(1, idSequence + 1); // o '+ 1' � para incrementar o
													// nextId
			updateID.setString(2, tableName); // N�o se esque�a de fazer o set
												// da 'tableName'
			updateID.execute();
			updateID.close();
			return idSequence;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao pegar ID para a tabela '" + tableName + "'", e);
		}
	}

}
