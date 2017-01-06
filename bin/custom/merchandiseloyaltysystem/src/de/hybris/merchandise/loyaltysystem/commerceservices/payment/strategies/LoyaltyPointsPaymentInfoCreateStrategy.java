package de.hybris.merchandise.loyaltysystem.commerceservices.payment.strategies;

import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.acceleratorservices.payment.data.CustomerInfoData;
import de.hybris.platform.acceleratorservices.payment.data.PaymentInfoData;
import de.hybris.platform.acceleratorservices.payment.data.SubscriptionInfoData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Map;

/**
 * Created by wladek on 1/4/17.
 */
public interface LoyaltyPointsPaymentInfoCreateStrategy {

    LoyaltyPointsPaymentInfoModel createLoyaltyPointsPaymentInfo(
            final AddressModel billingAddress, final CustomerModel customerModel
    );

    LoyaltyPointsPaymentInfoModel saveSubscription(
        CustomerModel customerModel, AddressData billingAddress
    );

}
