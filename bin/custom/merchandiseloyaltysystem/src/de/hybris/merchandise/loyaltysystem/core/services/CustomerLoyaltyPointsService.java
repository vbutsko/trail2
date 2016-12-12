package de.hybris.merchandise.loyaltysystem.core.services;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;

/**
 * Created by wladek on 11/16/16.
 */
public interface CustomerLoyaltyPointsService {

    void addLoyaltyPointsToCustomer(CustomerModel customerModel, OrderModel orderModel);

}
