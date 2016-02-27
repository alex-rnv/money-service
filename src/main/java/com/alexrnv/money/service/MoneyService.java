package com.alexrnv.money.service;

import com.alexrnv.money.dao.AccountDao;
import com.alexrnv.money.entity.Account;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

/**
 * @author Alex
 */
public class MoneyService {

    private static final String ACC_NOT_EXIST_MSG = "Account with id=%s not exist.";
    private static final String INVALID_AMOUNT_MSG = "Invalid numeric value: %s";

    @Inject
    private AccountDao accountDao;

    @GET
    @Path(value = "transfer")
    @Produces(MediaType.TEXT_PLAIN)
    public String transfer(@QueryParam("from") String accIdfrom,
                           @QueryParam("to") String accIdTo,
                           @QueryParam("amount") String amount) {
        Account from = accountDao.find(accIdfrom);
        if(from == null)
            throw new WebApplicationException(
                    String.format(ACC_NOT_EXIST_MSG, accIdfrom),
                    Response.Status.NOT_FOUND);

        Account to = accountDao.find(accIdfrom);
        if(to == null)
            throw new WebApplicationException(
                    String.format(ACC_NOT_EXIST_MSG, accIdTo),
                    Response.Status.NOT_FOUND);

        BigDecimal val;
        try {
            val = new BigDecimal(amount);
        } catch (NumberFormatException e) {
            throw new WebApplicationException(
                    String.format(INVALID_AMOUNT_MSG, amount),
                    Response.Status.BAD_REQUEST);
        }
        return transfer(from, to, val);
    }

    private String transfer(Account from, Account to, BigDecimal value) {
        return null;
    }
}
