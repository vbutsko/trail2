package de.hybris.merchandise.loyaltysystem.facades.populators;

import de.hybris.merchandise.loyaltysystem.facades.order.data.LPPaymentInfoData;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.commercefacades.order.converters.populator.ExtendedCartPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by wladek on 1/5/17.
 */
public class LPExtendedCartPopulator extends ExtendedCartPopulator {

    private Converter<LoyaltyPointsPaymentInfoModel, LPPaymentInfoData> loyaltyPointsPaymentInfoConverter;


    @Override
    public void populate(final CartModel source, final CartData target) {
        super.populate(source, target);
        addLPPaymentInformation(source, target);
    }

    public Converter<LoyaltyPointsPaymentInfoModel, LPPaymentInfoData> getLoyaltyPointsPaymentInfoConverter() {
        return loyaltyPointsPaymentInfoConverter;
    }

    @Required
    public void setLoyaltyPointsPaymentInfoConverter(Converter<LoyaltyPointsPaymentInfoModel, LPPaymentInfoData> loyaltyPointsPaymentInfoConverter) {
        this.loyaltyPointsPaymentInfoConverter = loyaltyPointsPaymentInfoConverter;
    }

    protected void addLPPaymentInformation(final AbstractOrderModel source, final AbstractOrderData prototype)
    {
        final PaymentInfoModel paymentInfo = source.getPaymentInfo();
        if (paymentInfo instanceof LoyaltyPointsPaymentInfoModel)
        {
            final LPPaymentInfoData paymentInfoData = getLoyaltyPointsPaymentInfoConverter().convert(
                    (LoyaltyPointsPaymentInfoModel) paymentInfo);
            prototype.setLPPaymentInfo(paymentInfoData);
        }
    }

}
