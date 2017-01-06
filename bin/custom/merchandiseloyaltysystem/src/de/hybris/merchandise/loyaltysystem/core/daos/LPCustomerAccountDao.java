package de.hybris.merchandise.loyaltysystem.core.daos;

import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;

/**
 * Created by wladek on 1/6/17.
 */
public interface LPCustomerAccountDao {
    LoyaltyPointsPaymentInfoModel findCreditCardPaymentInfoByCustomer(CustomerModel customerModel, String code);
}
