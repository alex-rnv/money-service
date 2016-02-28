package com.alexrnv.money.service;

import com.alexrnv.money.dao.AccountDao;
import com.alexrnv.money.dao.ExecutionException;
import com.alexrnv.money.entity.Account;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

/**
 * @author Alex
 */
@Path(value = "account")
public class AccountService {

    private static final String CREATED_MSG = "Account created %s";
    private static final String DELETED_MSG = "Account created %s";
    private static final String MISSED_MSG = "Account not found %s";
    private static final String EXCEPTION_MSG = "Error: %s";

    @Inject
    private AccountDao accountDao;

    @GET
    @Path(value = "create")
    @Produces(MediaType.TEXT_PLAIN)
    public String create(@QueryParam(value = "id") String id,
                         @QueryParam(value = "amount") BigDecimal amount) {
        Account account = new Account(id, amount);
        try {
            accountDao.create(account);
        } catch (ExecutionException e) {
            return String.format(EXCEPTION_MSG, e.getMessage());
        }
        return String.format(CREATED_MSG, id);
    }

    @GET
    @Path(value = "delete")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam(value = "id") String id) {
        try {
            if(accountDao.delete(id)) {
                return String.format(DELETED_MSG, id);
            } else {
                return String.format(MISSED_MSG, id);
            }
        } catch (ExecutionException e) {
            return String.format(EXCEPTION_MSG, e.getMessage());
        }
    }
}
