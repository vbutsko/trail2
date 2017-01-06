package de.hybris.merchandise.loyaltysystem.facades.impl;

import de.hybris.platform.acceleratorfacades.flow.impl.DefaultCheckoutFlowFacade;
import de.hybris.platform.commercefacades.order.data.CartData;

/**
 * Created by wladek on 1/6/17.
 */
public class LPCheckoutFlowFacade extends DefaultCheckoutFlowFacade {
    @Override
    public boolean hasNoPaymentInfo()
    {
        final CartData cartData = getCheckoutCart();
        return (cartData == null || (cartData.getPaymentInfo() == null && cartData.getLPPaymentInfo() == null));
    }
}
