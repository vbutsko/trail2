package de.hybris.merchandise.loyaltysystem.commerceservices.payment.impl;

import de.hybris.merchandise.loyaltysystem.commerceservices.payment.LoyaltyPointsPaymentService;
import de.hybris.merchandise.loyaltysystem.core.services.CustomerLoyaltyPointsService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by wladek on 12/22/16.
 */
public class LoyaltyPointsPaymentServiceImpl implements LoyaltyPointsPaymentService{

    @Resource
    private ModelService modelService;
    @Resource
    private CustomerLoyaltyPointsService defaultCustomerLoyaltyPointsService;

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    public void payForOrderByLoyaltyPoints(CustomerModel customerModel, BigDecimal amount) {
        if(defaultCustomerLoyaltyPointsService.checkLoyaltyPointsForOrder(customerModel, amount.doubleValue())){
                if(payByLoyaltyPoints(customerModel, amount)) {
                modelService.save(customerModel);
            }
        }else{
            throw new AdapterException("Don't have enough Loyalty Points to pay for Order");
        }
    }
    private boolean payByLoyaltyPoints(CustomerModel customerModel, BigDecimal amount){
        customerModel.setLoyaltyPoints(customerModel.getLoyaltyPoints() - amount.doubleValue());
        if(customerModel.getLoyaltyPoints() < 0.){
            throw new AdapterException("Don't have enough Loyalty Points to pay for Order after trying pay");
        }else{
            return true;
        }
    }

    public void setLpService(CustomerLoyaltyPointsService lpService){
        this.defaultCustomerLoyaltyPointsService = lpService;
    }

}
