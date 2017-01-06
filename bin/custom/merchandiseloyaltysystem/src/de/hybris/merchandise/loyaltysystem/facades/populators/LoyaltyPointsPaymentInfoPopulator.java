package de.hybris.merchandise.loyaltysystem.facades.populators;

import de.hybris.merchandise.loyaltysystem.facades.order.data.LPPaymentInfoData;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by wladek on 1/4/17.
 */
public class LoyaltyPointsPaymentInfoPopulator implements Populator<LoyaltyPointsPaymentInfoModel, LPPaymentInfoData>
{
    private Converter<AddressModel, AddressData> addressDataConverter;

    @Override
    public void populate(LoyaltyPointsPaymentInfoModel source, LPPaymentInfoData target) throws ConversionException {
        if (source.getBillingAddress() != null) {
            target.setBillingAddress(addressDataConverter.convert(source.getBillingAddress()));
        }
        target.setId(source.getPk().toString());
    }

    @Required
    public void setAddressDataConverter(Converter<AddressModel, AddressData> addressDataConverter) {
        this.addressDataConverter = addressDataConverter;
    }
}