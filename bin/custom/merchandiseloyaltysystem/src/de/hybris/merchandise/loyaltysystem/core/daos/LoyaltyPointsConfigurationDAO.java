package de.hybris.merchandise.loyaltysystem.core.daos;

import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsConfigurationModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;

import java.util.List;

/**
 * Created by wladek on 11/21/16.
 */
public interface LoyaltyPointsConfigurationDAO {

    List<LoyaltyPointsConfigurationModel> findLoyaltyPointsConfigurations();

    List<LoyaltyPointsConfigurationModel> findLoyaltyPointsConfigurationByCode(String code);

    List<LoyaltyPointsConfigurationModel> findLoyaltyPointsConfigurationByCurrency(CurrencyModel currencyModel);

}
