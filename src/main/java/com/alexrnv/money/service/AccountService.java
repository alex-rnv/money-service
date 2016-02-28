package com.alexrnv.money.service;

import com.alexrnv.money.entity.Account;

/**
 * @author Alex
 */

public interface AccountService {
    void create(Account account) throws ServiceException;
    Account find(String id) throws ServiceException;
}
