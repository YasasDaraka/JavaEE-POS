package lk.ijse.pos.listener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class ContextListener implements ServletContextListener {
    public static DataSource pool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            InitialContext context = new InitialContext();
            pool = (DataSource) context.lookup("java:comp/env/bean/MyBeanFactory");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
