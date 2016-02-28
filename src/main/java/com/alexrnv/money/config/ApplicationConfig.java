package com.alexrnv.money.config;

import com.alexrnv.money.controller.AccountController;
import com.alexrnv.money.controller.MoneyController;
import com.alexrnv.money.controller.SystemController;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created: 2/28/16 4:55 PM
 * Author: alex
 */
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        super(AccountController.class, MoneyController.class, SystemController.class);
        register(new ApplicationBinder());
        packages(true, "com.alexrnv.money");
    }
}
