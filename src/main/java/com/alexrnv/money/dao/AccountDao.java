package com.alexrnv.money.dao;

import com.alexrnv.money.entity.Account;

/**
 * @author Alex
 */
public interface AccountDao {
    Account find(String id);
    boolean save(Account account);
    boolean delete(Account account);
    boolean update(Account account);
}
