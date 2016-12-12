package de.hybris.merchandise.loyaltysystem.facades.impl;

import de.hybris.merchandise.loyaltysystem.core.services.impl.DefaultCustomerLoyaltyPointsService;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by wladek on 11/29/16.
 */
public class DefaultCustomerLoyaltyPointsFacade extends DefaultAcceleratorCheckoutFacade{

    private DefaultCustomerLoyaltyPointsService defaultCustomerLoyaltyPointsService;

    @Override
    protected OrderModel placeOrder(final CartModel cartModel) throws InvalidCartException{
        OrderModel orderModel = super.placeOrder(cartModel);
        defaultCustomerLoyaltyPointsService.addLoyaltyPointsToCustomer((CustomerModel) getUserService().getCurrentUser(), orderModel);
        return orderModel;
    }

    public DefaultCustomerLoyaltyPointsService getDefaultCustomerLoyaltyPointsService() {
        return defaultCustomerLoyaltyPointsService;
    }

    @Required
    public void setDefaultCustomerLoyaltyPointsService(DefaultCustomerLoyaltyPointsService defaultCustomerLoyaltyPointsService) {
        this.defaultCustomerLoyaltyPointsService = defaultCustomerLoyaltyPointsService;
    }

}
