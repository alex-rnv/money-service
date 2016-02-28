package com.alexrnv.money.service;

import com.alexrnv.money.dao.AccountDao;
import com.alexrnv.money.entity.Account;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;

/**
 * Created: 2/28/16 2:01 PM
 * Author: alex
 */
@Singleton
public class RDBMoneyServiceImpl implements MoneyService {
    private static final String ACC_NOT_EXIST_MSG = "Account with id=%s not exist.";
    private static final String NO_MONEY_MSG = "Not enough money to transfer from account %s, required: %s, available: %s";

    @Inject
    private EntityManager em;

    @Inject
    private AccountDao accountDao;

    @Override
    public void transfer(String accIdfrom, String accIdTo, BigDecimal value) throws ServiceException {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Account from = accountDao.find(accIdfrom);
            if(from == null)
                throw new ServiceException(String.format(ACC_NOT_EXIST_MSG, accIdfrom));

            Account to = accountDao.find(accIdTo);
            if(to == null)
                throw new ServiceException(String.format(ACC_NOT_EXIST_MSG, accIdTo));

            Account newFrom = add(from, value.negate());
            Account newTo = add(to, value);
            accountDao.update(newFrom);
            accountDao.update(newTo);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    protected Account add(Account account, BigDecimal val) throws ServiceException {
        BigDecimal newValue = account.getAmount().add(val);
        if(newValue.signum() < 0)
            throw new ServiceException(String.format(NO_MONEY_MSG, account.getId(), val, account.getAmount()));

        return new Account(account.getId(), newValue);
    }
}
