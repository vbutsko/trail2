package de.hybris.merchandise.loyaltysystem.core.daos.impl;

import de.hybris.merchandise.loyaltysystem.core.daos.LPCustomerAccountDao;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.beans.factory.annotation.Required;

import java.util.HashMap;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

/**
 * Created by wladek on 1/6/17.
 */
public class LPCustomerAccountDaoImpl implements LPCustomerAccountDao {
    private FlexibleSearchService flexibleSearchService;

    private static final String FIND_PAYMENT_INFO_BY_CUSTOMER_QUERY = "SELECT {" + LoyaltyPointsPaymentInfoModel.PK + "} FROM {"
            + LoyaltyPointsPaymentInfoModel._TYPECODE + "} WHERE {" + LoyaltyPointsPaymentInfoModel.USER + "} = ?customer AND {"
            + LoyaltyPointsPaymentInfoModel.PK + "} = ?pk AND {" + LoyaltyPointsPaymentInfoModel.DUPLICATE + "} = ?duplicate";

    @Override
    public LoyaltyPointsPaymentInfoModel findCreditCardPaymentInfoByCustomer(CustomerModel customerModel, String code) {
        validateParameterNotNull(customerModel, "Customer must not be null");
        final Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("customer", customerModel);
        queryParams.put("duplicate", Boolean.FALSE);
        queryParams.put("pk", PK.parse(code));
        final SearchResult<LoyaltyPointsPaymentInfoModel> result = getFlexibleSearchService().search(
                FIND_PAYMENT_INFO_BY_CUSTOMER_QUERY, queryParams);
        return result.getCount() > 0 ? result.getResult().get(0) : null;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }
    @Required
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
