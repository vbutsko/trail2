package de.hybris.merchandise.loyaltysystem.core.services;

import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;

/**
 * Created by wladek on 1/6/17.
 */
public interface LPCustomerAccountService {
    LoyaltyPointsPaymentInfoModel getLoyaltyPointsPaymentInfoForCode(CustomerModel customerModel, String code);
    void setDefaultPaymentInfo(CustomerModel customerModel, PaymentInfoModel paymentInfoModel);
}
