package com.alexrnv.money;

import com.alexrnv.money.config.ApplicationConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created: 2/28/16 3:05 PM
 * Author: alex
 */
public class MoneyApp {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(port);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter("javax.ws.rs.Application", ApplicationConfig.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
