package com.alexrnv.money;

import com.alexrnv.money.config.ApplicationConfig;
import com.alexrnv.money.entity.Account;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Integration test.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MoneyAppTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ApplicationConfig();
    }


    public Response createAccount(String id, String amount) {
        return target("account/create")
                .queryParam("id", id)
                .queryParam("amount", amount)
                .request()
                .get(Response.class);
    }

    public Response transfer(String fromId, String toId, String amount) {
        return target("transfer")
                .queryParam("from", fromId)
                .queryParam("to", toId)
                .queryParam("amount", amount)
                .request()
                .get(Response.class);
    }

    public Account find(String id) {
        return target("account/find")
                .queryParam("id", id)
                .request()
                .get(Response.class)
                .readEntity(Account.class);
    }


    /**
     * First test also initializes data in db
     */
    @Test
    public void test_1_CreateAccounts_Expect200() {
        Response r = createAccount("1", "1000");
        assertEquals(200, r.getStatus());
        r = createAccount("2", "100");
        assertEquals(200, r.getStatus());
        r = createAccount("3", "400");
        assertEquals(200, r.getStatus());
    }

    @Test
    public void test_2_CreateDuplicateAccount_Expect500() {
        Response r = createAccount("1", "30");
        assertEquals(500, r.getStatus());
    }

    @Test
    public void test_3_CreateAccountWrongId_Expect400() {
        Response r = createAccount(null, "30");
        assertEquals(400, r.getStatus());
        r = createAccount("", "30");
        assertEquals(400, r.getStatus());
    }

    @Test
    public void test_4_CreateAccountWronAmount_Expect400() {
        Response r = createAccount("45", "hundred");
        assertEquals(400, r.getStatus());
        r = createAccount("45", "");
        assertEquals(400, r.getStatus());
    }

    @Test
    public void test_5_TransferFromWrongId_Expect500() {
        Response r = transfer("45", "2", "200");
        assertEquals(500, r.getStatus());
    }

    @Test
    public void test_6_TransferToWrongId_Expect500() {
        Response r = transfer("1", "23", "200");
        assertEquals(500, r.getStatus());
    }

    @Test
    public void test_7_TransferWrongAmount_Expect400() {
        Response r = transfer("1", "2", "abc");
        assertEquals(400, r.getStatus());
        r = transfer("1", "2", "-100");
        assertEquals(400, r.getStatus());
    }

    @Test
    public void test_8_TransferOk_Expect200_CheckAmounts() {
        Response r = transfer("1", "2", "400");
        assertEquals(200, r.getStatus());
        Account a1 = find("1");
        Account a2 = find("2");
        assertEquals(600, a1.getAmount().intValue());
        assertEquals(500, a2.getAmount().intValue());

        r = transfer("1", "3", "100");
        assertEquals(200, r.getStatus());
        a1 = find("1");
        Account a3 = find("3");
        assertEquals(500, a1.getAmount().intValue());
        assertEquals(500, a3.getAmount().intValue());
    }


}