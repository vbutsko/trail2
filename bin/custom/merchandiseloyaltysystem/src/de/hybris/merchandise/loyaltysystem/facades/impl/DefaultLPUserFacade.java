package de.hybris.merchandise.loyaltysystem.facades.impl;

import de.hybris.merchandise.loyaltysystem.core.services.LPCustomerAccountService;
import de.hybris.merchandise.loyaltysystem.facades.LPUserFacade;
import de.hybris.merchandise.loyaltysystem.facades.order.data.LPPaymentInfoData;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import org.springframework.beans.factory.annotation.Required;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

/**
 * Created by wladek on 1/6/17.
 */
public class DefaultLPUserFacade implements LPUserFacade {
    private LPCustomerAccountService lpCustometAccountService;
    private CheckoutCustomerStrategy checkoutCustomerStrategy;

    @Override
    public void setDefaultLPPaymentInfo(LPPaymentInfoData paymentInfoData) {
        validateParameterNotNullStandardMessage("paymentInfoData", paymentInfoData);
        final CustomerModel currentCustomer = getCurrentUserForCheckout();
        final LoyaltyPointsPaymentInfoModel lpPaymentInfoModel = getLpCustometAccountService().getLoyaltyPointsPaymentInfoForCode(
                currentCustomer, paymentInfoData.getId());
        if (lpPaymentInfoModel != null)
        {
            getLpCustometAccountService().setDefaultPaymentInfo(currentCustomer, lpPaymentInfoModel);
        }
    }

    public LPCustomerAccountService getLpCustometAccountService() {
        return lpCustometAccountService;
    }
    @Required
    public void setLpCustometAccountService(LPCustomerAccountService lpCustometAccountService) {
        this.lpCustometAccountService = lpCustometAccountService;
    }
    protected CustomerModel getCurrentUserForCheckout()
    {
        return getCheckoutCustomerStrategy().getCurrentUserForCheckout();
    }

    public CheckoutCustomerStrategy getCheckoutCustomerStrategy() {
        return checkoutCustomerStrategy;
    }
    @Required
    public void setCheckoutCustomerStrategy(CheckoutCustomerStrategy checkoutCustomerStrategy) {
        this.checkoutCustomerStrategy = checkoutCustomerStrategy;
    }
}
