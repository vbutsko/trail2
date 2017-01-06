package de.hybris.merchandise.loyaltysystem.facades.impl;

import de.hybris.merchandise.loyaltysystem.core.services.LPCustomerAccountService;
import de.hybris.merchandise.loyaltysystem.core.services.impl.DefaultCustomerLoyaltyPointsService;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.math.BigDecimal;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

/**
 * Created by wladek on 11/29/16.
 */
public class DefaultCustomerLoyaltyPointsFacade extends DefaultAcceleratorCheckoutFacade{

    private DefaultCustomerLoyaltyPointsService defaultCustomerLoyaltyPointsService;
    private LPCustomerAccountService lpCustometAccountService;
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

    @Override
    public boolean setPaymentDetails(final String paymentInfoId)
    {
        validateParameterNotNullStandardMessage("paymentInfoId", paymentInfoId);

        if (checkIfCurrentUserIsTheCartUser())
        {
            if (StringUtils.isNotBlank(paymentInfoId))
            {
                final CustomerModel currentUserForCheckout = getCurrentUserForCheckout();
                final CreditCardPaymentInfoModel ccPaymentInfoModel = getCustomerAccountService().getCreditCardPaymentInfoForCode(
                        currentUserForCheckout, paymentInfoId);
                final LoyaltyPointsPaymentInfoModel lpPaymentInfoModel = getLpCustometAccountService().getLoyaltyPointsPaymentInfoForCode(
                        currentUserForCheckout, paymentInfoId);
                final PaymentInfoModel paymentInfoModel = (ccPaymentInfoModel == null) ? lpPaymentInfoModel : ccPaymentInfoModel;
                final CartModel cart = getCart();
                if (paymentInfoId != null)
                {
                    final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
                    parameter.setEnableHooks(true);
                    parameter.setCart(cart);
                    parameter.setPaymentInfo(paymentInfoModel);
                    return getCommerceCheckoutService().setPaymentInfo(parameter);
                }
            }
        }

        return false;
    }

    public LPCustomerAccountService getLpCustometAccountService() {
        return lpCustometAccountService;
    }
    @Required
    public void setLpCustometAccountService(LPCustomerAccountService lpCustometAccountService) {
        this.lpCustometAccountService = lpCustometAccountService;
    }

    @Override
    public boolean authorizePayment(String securityCode){
        final CartModel cartModel = getCart();
        if (checkIfCurrentUserIsTheCartUser())
        {
            PaymentInfoModel paymentInfo = cartModel.getPaymentInfo();
            if(paymentInfo != null &&
                    ( (paymentInfo instanceof CreditCardPaymentInfoModel  && StringUtils.isNotBlank(((CreditCardPaymentInfoModel)paymentInfo).getSubscriptionId()))
                            || (paymentInfo instanceof LoyaltyPointsPaymentInfoModel)) ) {
                final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
                parameter.setEnableHooks(true);
                parameter.setCart(cartModel);
                parameter.setSecurityCode(securityCode);
                parameter.setPaymentProvider(getPaymentProvider());
                final PaymentTransactionEntryModel paymentTransactionEntryModel = getCommerceCheckoutService().authorizePayment(
                        parameter);

                return paymentTransactionEntryModel != null
                        && (TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.REVIEW
                        .name().equals(paymentTransactionEntryModel.getTransactionStatus()));
            }
        }
        return false;
    }
}
