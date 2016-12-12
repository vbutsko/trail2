package de.hybris.merchandise.loyaltysystem.core.services.impl;


import de.hybris.merchandise.loyaltysystem.core.daos.LoyaltyPointsConfigurationDAO;
import de.hybris.merchandise.loyaltysystem.core.services.CustomerLoyaltyPointsService;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsConfigurationModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by wladek on 11/16/16.
 */
public class DefaultCustomerLoyaltyPointsService implements CustomerLoyaltyPointsService {

    private ModelService modelService;

    private LoyaltyPointsConfigurationDAO loyaltyPointsConfigurationDAO;

    @Override
    public void addLoyaltyPointsToCustomer(CustomerModel customerModel, OrderModel orderModel) {
        List<LoyaltyPointsConfigurationModel> configs = loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurationByCurrency(orderModel.getCurrency());
        if(configs == null || configs.isEmpty()){
            throw new IllegalArgumentException("there is no LoyaltyPointsConfiguration for this Currency");
        }
        accureLoyaltyPoints(customerModel, orderModel, configs.get(0));
        modelService.save(customerModel);
    }

    private void accureLoyaltyPoints(CustomerModel customerModel, OrderModel orderModel, LoyaltyPointsConfigurationModel configurationModel){
        Double customerExtraLoyaltyPoints = new Double(0.0);
        switch(configurationModel.getConfigurationType()){
            case ABSOLUTE:
                customerExtraLoyaltyPoints = configurationModel.getValue();
                break;
            case RELATIVE:
                customerExtraLoyaltyPoints = orderModel.getSubtotal() * ( configurationModel.getValue() / 100. );
                break;
            default:
                throw new IllegalArgumentException("incorrect value for LoyaltyPointsConfigurationType enumeration");
        }
        if(customerModel.getLoyaltyPoints() == null){
            customerModel.setLoyaltyPoints(Double.valueOf(0.0));
        }
        customerModel.setLoyaltyPoints(customerModel.getLoyaltyPoints() + customerExtraLoyaltyPoints);
    }

    @Required
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    @Required
    public void setLoyaltyPointsConfigurationDAO(LoyaltyPointsConfigurationDAO loyaltyPointsConfigurationDAO) {
        this.loyaltyPointsConfigurationDAO = loyaltyPointsConfigurationDAO;
    }

}
