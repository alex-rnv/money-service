package com.alexrnv.money.service;

import java.math.BigDecimal;

/**
 * @author Alex
 */
public interface MoneyService {
    void transfer(String fromId, String toId, BigDecimal value) throws ServiceException;
}
