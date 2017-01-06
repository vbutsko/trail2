package de.hybris.merchandise.loyaltysystem.facades;

import de.hybris.merchandise.loyaltysystem.facades.order.data.LPPaymentInfoData;

/**
 * Created by wladek on 1/6/17.
 */
public interface LPUserFacade {
    void setDefaultLPPaymentInfo(LPPaymentInfoData paymentInfo);
}
