package com.alexrnv.money.controller;

import com.alexrnv.money.entity.Account;
import com.alexrnv.money.service.AccountService;
import com.alexrnv.money.service.ServiceException;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;

import static com.alexrnv.money.controller.ControllerUtil.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created: 2/28/16 1:24 PM
 * Author: alex
 */
@Path(value = "account")
public class AccountController {

    private static final Logger LOG = getLogger(AccountController.class);


    @Inject
    private AccountService accountService;

    @GET
    @Path(value = "create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(@QueryParam(value = "id") String id,
                          @QueryParam(value = "amount") String amount) {

        LOG.info("New account creation request, id: {}, amount: {}", id, amount);

        checkID(id);
        BigDecimal val = checkTransferAmount(amount);
        Account account = new Account(id, val);
        try {
            accountService.create(account);
        } catch (ServiceException e) {
            LOG.error("", e);
            //no special response formatting for internal service
            String msg = String.format(EXCEPTION_MSG, e.getMessage());
            throw new WebApplicationException(
                    msg, Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg).build());
        }
        return Response.status(Response.Status.OK).entity(String.format(CREATED_MSG, id)).build();
    }

    @GET
    @Path(value = "find")
    @Produces(MediaType.APPLICATION_JSON)
    public Account find(@QueryParam(value = "id") String id) {
        LOG.info("Get account request received, id: {}", id);

        checkID(id);

        try {
            Account account = accountService.find(id);
            if(account == null) {
                throw new WebApplicationException(
                        Response.status(Response.Status.OK).entity("{}").build());
            }
            return account;
        } catch (ServiceException e) {
            LOG.error("", e);
            //no special response formatting for internal service
            String msg = String.format(EXCEPTION_MSG, e.getMessage());
            throw new WebApplicationException(
                    msg, Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg).build());
        }
    }
}
