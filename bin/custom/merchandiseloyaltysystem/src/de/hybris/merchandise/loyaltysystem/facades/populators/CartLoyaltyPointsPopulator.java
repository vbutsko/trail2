package de.hybris.merchandise.loyaltysystem.facades.populators;

import de.hybris.merchandise.loyaltysystem.core.services.CustomerLoyaltyPointsService;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;

/**
 * Created by wladek on 12/26/16.
 */
public class CartLoyaltyPointsPopulator implements Populator<CartModel, CartData>
{
    @Resource
    private UserService userService;
    @Resource
    private CustomerLoyaltyPointsService defaultCustomerLoyaltyPointsService;

    @Override
    public void populate(final CartModel source, final CartData target) throws ConversionException
    {
        target.setCanBePaidByLoyaltyPoints(defaultCustomerLoyaltyPointsService.checkLoyaltyPointsForOrder((CustomerModel) userService.getCurrentUser(), source.getTotalPrice()));
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public void setLpService(CustomerLoyaltyPointsService defaultCustomerLoyaltyPointsService){
        this.defaultCustomerLoyaltyPointsService = defaultCustomerLoyaltyPointsService;
    }
}