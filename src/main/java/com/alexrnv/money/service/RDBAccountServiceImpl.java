package com.alexrnv.money.service;

import com.alexrnv.money.dao.AccountDao;
import com.alexrnv.money.entity.Account;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created: 2/28/16 1:30 PM
 * Author: alex
 */
@Singleton
public class RDBAccountServiceImpl implements AccountService {

    private static final Logger LOG = getLogger(RDBAccountServiceImpl.class);

    @Inject
    private EntityManager em;

    @Inject
    private AccountDao accountDao;

    @Override
    public void create(Account account) throws ServiceException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            accountDao.create(account);
            transaction.commit();
            LOG.info("Account saved {}", account);
        } catch (Exception e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public Account find(String id) throws ServiceException {
        return accountDao.find(id);
    }

}
