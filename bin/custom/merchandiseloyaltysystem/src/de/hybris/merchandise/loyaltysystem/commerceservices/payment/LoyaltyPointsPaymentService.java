package de.hybris.merchandise.loyaltysystem.commerceservices.payment;


import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.methods.PaymentMethod;

import java.math.BigDecimal;

/**
 * Created by wladek on 12/22/16.
 */
public interface LoyaltyPointsPaymentService extends PaymentMethod {
    void payForOrderByLoyaltyPoints(CustomerModel customerModel, BigDecimal amount) throws AdapterException;
}
