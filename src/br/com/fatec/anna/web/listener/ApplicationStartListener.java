package br.com.fatec.anna.web.listener;

import javax.servlet.*;

import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */
public class ApplicationStartListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ContextSpecifier.setContext("br.com.fatec.anna");
		ConfigDBMapper.setDefaultConnectionName("pg");
		LiquibaseRunnerService.run();
		
	}

}
