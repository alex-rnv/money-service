package com.alexrnv.money.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Just simplest way to stop test app.
 */
@Path(value = "system")
public class SystemController {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SystemController.class);

    @GET
    @Path(value = "kill")
    public void transfer() {
        LOG.info("Master wants me to kill myself. Bye!");
        System.exit(0);
    }
}
