package com.alexrnv.money.dao;

import com.alexrnv.money.entity.Account;

/**
 * @author Alex
 */
public interface AccountDao {
    Account find(String id);
    void create(Account account);
    void update(Account account);
}
