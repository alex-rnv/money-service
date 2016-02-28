package com.alexrnv.money.dao;

import com.alexrnv.money.entity.Account;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Created: 2/28/16 1:13 PM
 * Author: alex
 */
@Singleton
public class RDBAccountDaoImpl implements AccountDao {

    @Inject
    private EntityManager em;

    @Override
    public Account find(String id) {
        return em.find(Account.class, id);
    }

    @Override
    public void create(Account account) {
        em.persist(account);
        em.flush();
    }

    @Override
    public void update(Account account) {
        em.merge(account);
        em.flush();
    }
}
