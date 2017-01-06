package de.hybris.merchandise.loyaltysystem.commerceservices.payment.strategies.impl;

import de.hybris.merchandise.loyaltysystem.commerceservices.payment.strategies.LoyaltyPointsPaymentInfoCreateStrategy;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.customer.CustomerEmailResolutionService;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.Map;
import java.util.UUID;


/**
 * Created by wladek on 1/4/17.
 */
public class LoyaltyPointsPaymentInfoCreateStrategyImpl implements LoyaltyPointsPaymentInfoCreateStrategy {

    private ModelService modelService;
    private CommonI18NService commonI18NService;
    private CustomerEmailResolutionService customerEmailResolutionService;
    private UserService userService;

    @Override
    public LoyaltyPointsPaymentInfoModel createLoyaltyPointsPaymentInfo(AddressModel billingAddress, CustomerModel customerModel) {
        final LoyaltyPointsPaymentInfoModel loyaltyPointsPaymentInfoModel = getModelService().create(LoyaltyPointsPaymentInfoModel.class);
        loyaltyPointsPaymentInfoModel.setBillingAddress(billingAddress);
        loyaltyPointsPaymentInfoModel.setCode(customerModel.getUid() + "_" + UUID.randomUUID());
        loyaltyPointsPaymentInfoModel.setUser(customerModel);
        //loyaltyPointsPaymentInfoModel.setSubscriptionId(parameters.get("paySubscriptionCreateReply_subscriptionID"));

        return loyaltyPointsPaymentInfoModel;
    }

    @Override
    public LoyaltyPointsPaymentInfoModel saveSubscription(CustomerModel customerModel, AddressData billingAddress) {
        final AddressModel billingAddressTemp = getModelService().create(AddressModel.class);
        billingAddressTemp.setFirstname(billingAddress.getFirstName());
        billingAddressTemp.setLastname(billingAddress.getLastName());
        billingAddressTemp.setLine1(billingAddress.getLine1());
        billingAddressTemp.setLine2(billingAddress.getLine2());
        billingAddressTemp.setTown(billingAddress.getTown());
        billingAddressTemp.setPostalcode(billingAddress.getPostalCode());

        if (StringUtils.isNotBlank(billingAddress.getTitleCode()))
        {
            billingAddressTemp.setTitle(getUserService().getTitleForCode(billingAddress.getTitleCode()));
        }
        final CountryModel country = getCommonI18NService().getCountry(billingAddress.getCountry().getIsocode());
        billingAddressTemp.setCountry(country);
        if (billingAddress.getRegion() != null &&  StringUtils.isNotBlank(billingAddress.getRegion().getIsocode()))
        {
            billingAddressTemp.setRegion(getCommonI18NService().getRegion(country,
                    country.getIsocode() + "-" + billingAddress.getRegion().getIsocode()));
        }

        final String email = getCustomerEmailResolutionService().getEmailForCustomer(customerModel);

        billingAddressTemp.setEmail(email);

        final LoyaltyPointsPaymentInfoModel loyaltyPointsPaymentInfoModel = this.createLoyaltyPointsPaymentInfo(billingAddressTemp, customerModel);


        billingAddressTemp.setOwner(loyaltyPointsPaymentInfoModel);

        if (CustomerType.GUEST.equals(customerModel.getType()))
        {
            final StringBuilder name = new StringBuilder();
            if (!StringUtils.isBlank(billingAddressTemp.getFirstname()))
            {
                name.append(billingAddressTemp.getFirstname());
                name.append(' ');
            }
            if (!StringUtils.isBlank(billingAddressTemp.getLastname()))
            {
                name.append(billingAddressTemp.getLastname());
            }
            customerModel.setName(name.toString());
            getModelService().save(customerModel);
        }

        getModelService().saveAll(loyaltyPointsPaymentInfoModel, billingAddressTemp);
        getModelService().refresh(customerModel);

        return loyaltyPointsPaymentInfoModel;
    }

    protected ModelService getModelService()
    {
        return modelService;
    }

    @Required
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    protected CommonI18NService getCommonI18NService()
    {
        return commonI18NService;
    }

    @Required
    public void setCommonI18NService(final CommonI18NService commonI18NService)
    {
        this.commonI18NService = commonI18NService;
    }


    protected CustomerEmailResolutionService getCustomerEmailResolutionService()
    {
        return customerEmailResolutionService;
    }

    @Required
    public void setCustomerEmailResolutionService(final CustomerEmailResolutionService customerEmailResolutionService)
    {
        this.customerEmailResolutionService = customerEmailResolutionService;
    }

    protected UserService getUserService()
    {
        return userService;
    }

    @Required
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }
}
