package com.alexrnv.money.controller;

import com.alexrnv.money.service.MoneyService;
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
 * Created: 2/28/16 1:23 PM
 * Author: alex
 */
@Path(value = "transfer")
public class MoneyController {

    private static final Logger LOG = getLogger(MoneyController.class);


    @Inject
    private MoneyService moneyService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response transfer(@QueryParam("from") String accIdfrom,
                           @QueryParam("to") String accIdTo,
                           @QueryParam("amount") String amount) {

        LOG.info("New transfer request, from: {}, to: {}, amount: {}", accIdfrom, accIdTo, amount);

        checkID(accIdfrom);
        checkID(accIdTo);
        BigDecimal val = checkTransferAmount(amount);
        try {
            moneyService.transfer(accIdfrom, accIdTo, val);
            return Response.status(Response.Status.OK).entity(TRANSFERRED_MSG).build();
        } catch (ServiceException e) {
            //no special response formatting for internal service
            LOG.error("", e);
            String msg = String.format(EXCEPTION_MSG, e.getMessage());
            throw new WebApplicationException(
                    msg, Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg).build());
        }
    }

}
