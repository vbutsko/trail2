package de.hybris.merchandise.loyaltysystem.core.daos.impl;

import de.hybris.merchandise.loyaltysystem.core.daos.LoyaltyPointsConfigurationDAO;

import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsConfigurationModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "loyaltyPointsConfigurationDAO")
public class DefaultLoyaltyPointsConfigurationDAO implements LoyaltyPointsConfigurationDAO {

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<LoyaltyPointsConfigurationModel> findLoyaltyPointsConfigurations() {
        final String queryString =
            "SELECT {p:" + LoyaltyPointsConfigurationModel.PK + "} "
            + "FROM {" + LoyaltyPointsConfigurationModel._TYPECODE +" AS p} ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        return flexibleSearchService.<LoyaltyPointsConfigurationModel> search(query).getResult();
    }

    @Override
    public List<LoyaltyPointsConfigurationModel> findLoyaltyPointsConfigurationByCode(String code) {
        final String queryString =
            "SELECT {p:" + LoyaltyPointsConfigurationModel.PK + "} "
            + "FROM {" + LoyaltyPointsConfigurationModel._TYPECODE + " AS p} "
            + "WHERE " + "{p:" + LoyaltyPointsConfigurationModel.CODE + "}=?code ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("code", code);
        return flexibleSearchService.<LoyaltyPointsConfigurationModel> search(query).getResult();
    }

    @Override
    public List<LoyaltyPointsConfigurationModel> findLoyaltyPointsConfigurationByCurrency(CurrencyModel currencyModel) {
        final String queryString =
                "SELECT {p:" + LoyaltyPointsConfigurationModel.PK + "} "
                        + "FROM {" + LoyaltyPointsConfigurationModel._TYPECODE + " AS p} "
                        + "WHERE " + "{p:" + LoyaltyPointsConfigurationModel.CURRENCY + "}=?currency ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        query.addQueryParameter("currency", currencyModel);
        return flexibleSearchService.<LoyaltyPointsConfigurationModel> search(query).getResult();
    }
}
