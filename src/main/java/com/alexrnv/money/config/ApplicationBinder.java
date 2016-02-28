package com.alexrnv.money.config;

import com.alexrnv.money.dao.AccountDao;
import com.alexrnv.money.dao.RDBAccountDaoImpl;
import com.alexrnv.money.service.AccountService;
import com.alexrnv.money.service.MoneyService;
import com.alexrnv.money.service.RDBAccountServiceImpl;
import com.alexrnv.money.service.RDBMoneyServiceImpl;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created: 2/28/16 4:53 PM
 * Author: alex
 */
public class ApplicationBinder extends AbstractBinder implements Factory<EntityManager> {

    private final EntityManager entityManager = Persistence
            .createEntityManagerFactory("money-service-unit").createEntityManager();

    @Override
    public EntityManager provide() {
        return entityManager;
    }

    @Override
    public void dispose(EntityManager instance) {
        instance.close();
    }

    @Override
    protected void configure() {
        bind(RDBAccountDaoImpl.class).to(AccountDao.class).in(Singleton.class);
        bind(RDBAccountServiceImpl.class).to(AccountService.class).in(Singleton.class);
        bind(RDBMoneyServiceImpl.class).to(MoneyService.class).in(Singleton.class);
        bindFactory(this).to(EntityManager.class).in(Singleton.class);
    }

}
