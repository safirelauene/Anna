package br.com.fatec.anna.web.listener;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.fatec.anna.api.dao.RoleDAO;
import br.com.fatec.anna.api.dao.UsuarioDAO;
import br.com.fatec.anna.api.entity.Role;
import br.com.fatec.anna.api.entity.Usuario;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

import com.google.common.collect.Lists;

import br.com.fatec.anna.core.service.GeradorIdService;

/**
 * @author Rodrigo Takeshi
 * 
 * @version 1.0
 */
public class ApplicationConfigListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		UsuarioDAO usuarioDAO = (UsuarioDAO) ImplementationFinder.getImpl(UsuarioDAO.class);
		Usuario admin = usuarioDAO.findByLogin("admin");
		GeradorIdService gis;

		if (admin == null) {
			RoleDAO roleDao = ImplementationFinder.getImpl(RoleDAO.class);
			//UsuarioRoleDAO usuarioRoleDao = ImplementationFinder.getImpl(UsuarioRoleDAO.class);

		//	admin = usuarioDAO.save(new Usuario(null, "admin", "admin", "admin"));
		//	List<Role> roles = Lists.newArrayList(roleDao.save(new Role(gis.getNextId(Role.TABELA), "admin")),
		//			roleDao.save(new Role(null, "cadastrar_usuarios")));

		//	admin.setRoles(roles);
		//	usuarioRoleDao.atualisarRoles(admin);
		}
	}

}
