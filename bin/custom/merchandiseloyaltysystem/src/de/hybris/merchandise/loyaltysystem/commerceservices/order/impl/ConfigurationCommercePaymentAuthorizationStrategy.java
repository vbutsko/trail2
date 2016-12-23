package de.hybris.merchandise.loyaltysystem.commerceservices.order.impl;

import de.hybris.merchandise.loyaltysystem.commerceservices.payment.LoyaltyPointsPaymentService;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.commerceservices.order.CommercePaymentAuthorizationStrategy;
import de.hybris.platform.commerceservices.order.impl.DefaultCommercePaymentAuthorizationStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by wladek on 12/21/16.
 */
public class ConfigurationCommercePaymentAuthorizationStrategy implements CommercePaymentAuthorizationStrategy {

    private DefaultCommercePaymentAuthorizationStrategy ccStrategy;
    private LoyaltyPointsCommercePaymentAuthorizationStrategy lpStrategy;

    @Override
    public PaymentTransactionEntryModel authorizePaymentAmount(CommerceCheckoutParameter parameter) {
        CartModel cartModel = parameter.getCart();
        PaymentInfoModel paymentInfoModel = cartModel.getPaymentInfo();

        if(paymentInfoModel instanceof CreditCardPaymentInfoModel){
            return ccStrategy.authorizePaymentAmount(parameter);
        }else if(paymentInfoModel instanceof LoyaltyPointsPaymentInfoModel){
            return lpStrategy.authorizePaymentAmount(parameter);
        }

        return null;
    }

    public DefaultCommercePaymentAuthorizationStrategy getCcStrategy() {
        return ccStrategy;
    }
    public LoyaltyPointsCommercePaymentAuthorizationStrategy getLpStrategy(){
        return lpStrategy;
    }
    @Required
    public void setCcStrategy(DefaultCommercePaymentAuthorizationStrategy ccStrategy) {
        this.ccStrategy = ccStrategy;
    }
    @Required
    public void setLpStrategy(LoyaltyPointsCommercePaymentAuthorizationStrategy lpStrategy) {
        this.lpStrategy = lpStrategy;
    }
}
