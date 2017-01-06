package de.hybris.merchandise.loyaltysystem.facades.impl;

import de.hybris.merchandise.loyaltysystem.commerceservices.payment.strategies.LoyaltyPointsPaymentInfoCreateStrategy;
import de.hybris.merchandise.loyaltysystem.facades.order.data.LPPaymentInfoData;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.acceleratorfacades.payment.data.PaymentSubscriptionResultData;
import de.hybris.platform.acceleratorservices.payment.data.PaymentSubscriptionResultItem;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

import java.util.Map;

/**
 * Created by wladek on 1/3/17.
 */
public class LoyaltyPointsPaymentFacade {

    private LoyaltyPointsPaymentInfoCreateStrategy loyaltyPointsPaymentInfoCreateStrategy;
    private CheckoutCustomerStrategy checkoutCustomerStrategy;
    private Converter<LoyaltyPointsPaymentInfoModel, LPPaymentInfoData> lPPaymentInfoDataConverter;
    //private Converter<LoyaltyPointsPaymentInfoModel, LPPaymentInfoData> lPPaymentInfoDataConverter;

    public LPPaymentInfoData saveLPPaymentInfo(final AddressData addressData, final Map<String, String> resultMap){
        final CustomerModel customerModel = getCurrentUserForCheckout();

        final LoyaltyPointsPaymentInfoModel paymentModel = loyaltyPointsPaymentInfoCreateStrategy.saveSubscription(customerModel, addressData);
        if (paymentModel != null)
        {
            return getLPPaymentInfoDataConverter().convert(paymentModel);
        }
        return null;
    }

    protected CheckoutCustomerStrategy getCheckoutCustomerStrategy()
    {
        return checkoutCustomerStrategy;
    }

    @Required
    public void setCheckoutCustomerStrategy(final CheckoutCustomerStrategy checkoutCustomerStrategy)
    {
        this.checkoutCustomerStrategy = checkoutCustomerStrategy;
    }

    protected CustomerModel getCurrentUserForCheckout()
    {
        return getCheckoutCustomerStrategy().getCurrentUserForCheckout();
    }

    public LoyaltyPointsPaymentInfoCreateStrategy getLoyaltyPointsPaymentInfoCreateStrategy() {
        return loyaltyPointsPaymentInfoCreateStrategy;
    }
    @Required
    public void setLoyaltyPointsPaymentInfoCreateStrategy(LoyaltyPointsPaymentInfoCreateStrategy loyaltyPointsPaymentInfoCreateStrategy) {
        this.loyaltyPointsPaymentInfoCreateStrategy = loyaltyPointsPaymentInfoCreateStrategy;
    }
    protected Converter<LoyaltyPointsPaymentInfoModel, LPPaymentInfoData> getLPPaymentInfoDataConverter()
    {
        return lPPaymentInfoDataConverter;
    }

    @Required
    public void setLPPaymentInfoDataConverter(
            final Converter<LoyaltyPointsPaymentInfoModel, LPPaymentInfoData> lPPaymentInfoDataConverter)
    {
        this.lPPaymentInfoDataConverter = lPPaymentInfoDataConverter;
    }
}
