package myssm.listeners;

import myssm.ioc.BeanFactory;
import myssm.ioc.XmlClassPathApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author kayai
 * @version 1.0
 * @date: 2022/4/22 11:18
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        String path = application.getInitParameter("contextConfigLocation");
        BeanFactory beanFactory = new XmlClassPathApplicationContext(path);
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
