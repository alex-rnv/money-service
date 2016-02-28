package com.alexrnv.money.controller;

import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created: 2/28/16 5:53 PM
 * Author: alex
 */
class ControllerUtil {
    private static final Logger LOG = getLogger(ControllerUtil.class);

    static final String INVALID_ID_MSG = "Invalid identifier: \"%s\"";
    static final String INVALID_AMOUNT_MSG = "Invalid numeric value: %s";
    static final String NEGATIVE_AMOUNT_MSG = "Negative numeric value not allowed: %s";
    static final String TRANSFERRED_MSG = "Money transfer complete";
    static final String CREATED_MSG = "Account created %s";
    static final String EXCEPTION_MSG = "Error: %s";


    /**
     * Simplest id check, any character except blank allowed for test app.
     */
    static void checkID(String id) {
        if(StringUtil.isBlank(id)) {
            String msg = String.format(INVALID_ID_MSG, id);
            LOG.error(msg);
            throw new WebApplicationException(
                    msg, Response.status(Response.Status.BAD_REQUEST).entity(msg).build());
        }
    }

    static BigDecimal checkTransferAmount(String num) {
        BigDecimal bd = null;
        if(StringUtil.isBlank(num)) {
            fail(INVALID_AMOUNT_MSG, num);
        }
        try {
            bd = new BigDecimal(num);
            if(bd.signum() < 0) {
                fail(NEGATIVE_AMOUNT_MSG, num);
            }
        } catch (NumberFormatException e) {
            fail(INVALID_AMOUNT_MSG, num);
        }
        return bd;
    }

    private static void fail(String formatMsg, String num) {
        String msg =  String.format(formatMsg, num);
        LOG.error(msg);
        throw new WebApplicationException(
                msg, Response.status(Response.Status.BAD_REQUEST).entity(msg).build());
    }
}
