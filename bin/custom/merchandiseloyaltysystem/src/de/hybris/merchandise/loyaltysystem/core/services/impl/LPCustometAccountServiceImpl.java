package de.hybris.merchandise.loyaltysystem.core.services.impl;

import de.hybris.merchandise.loyaltysystem.core.daos.LPCustomerAccountDao;
import de.hybris.merchandise.loyaltysystem.core.services.LPCustomerAccountService;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

/**
 * Created by wladek on 1/6/17.
 */
public class LPCustometAccountServiceImpl implements LPCustomerAccountService{
    private LPCustomerAccountDao lpCustomerAccountDao;
    private ModelService modelService;

    public LoyaltyPointsPaymentInfoModel getLoyaltyPointsPaymentInfoForCode(CustomerModel customerModel, String code){
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        return getLpCustomerAccountDao().findCreditCardPaymentInfoByCustomer(customerModel, code);
    }

    @Override
    public void setDefaultPaymentInfo(CustomerModel customerModel, PaymentInfoModel paymentInfoModel) {
        validateParameterNotNull(customerModel, "Customer model cannot be null");
        validateParameterNotNull(paymentInfoModel, "Payment info model cannot be null");
        if (customerModel.getPaymentInfos().contains(paymentInfoModel))
        {
            customerModel.setDefaultPaymentInfo(paymentInfoModel);
            getModelService().save(customerModel);
            getModelService().refresh(customerModel);
        }
    }

    public LPCustomerAccountDao getLpCustomerAccountDao() {
        return lpCustomerAccountDao;
    }
    @Required
    public void setLpCustomerAccountDao(LPCustomerAccountDao lpCustomerAccountDao) {
        this.lpCustomerAccountDao = lpCustomerAccountDao;
    }

    public ModelService getModelService() {
        return modelService;
    }
    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
