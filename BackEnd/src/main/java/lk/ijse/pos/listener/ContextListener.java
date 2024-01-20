package lk.ijse.pos.listener;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    public static BasicDataSource pool;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        pool = new BasicDataSource();
        pool.setDriverClassName("com.mysql.cj.jdbc.Driver");
        pool.setUrl("jdbc:mysql://localhost:3306/Javaee");
        pool.setUsername("root");
        pool.setPassword("1234");
        pool.setInitialSize(10);
        pool.setMaxTotal(20);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
