package de.hybris.merchandise.loyaltysystem.core.services.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.merchandise.loyaltysystem.enums.LoyaltyPointsConfigurationType;
import de.hybris.merchandise.loyaltysystem.core.daos.LoyaltyPointsConfigurationDAO;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsConfigurationModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultCustomerLoyaltyPointsServiceUnitTest {

    @Mock
    private LoyaltyPointsConfigurationDAO loyaltyPointsConfigurationDAO;
    @Mock
    private ModelService modelService;

    @Captor
    private ArgumentCaptor<CustomerModel> argCaptor;

    @InjectMocks
    private DefaultCustomerLoyaltyPointsService customerLoyaltyPointsService;
    
    private LoyaltyPointsConfigurationModel loyaltyPointsConfigurationModel;
    private static final String LPCONF_CODE = "testname";
    private static final Double LPCONF_VALUE = 10.;
    private CurrencyModel currencyModel;
    private static final String CURRENCY_SYMBOL = "testsymbol";
    private static final String CURRENCY_ISOCODE = "testisocode";

    private OrderModel orderModel;
    private CustomerModel customerModel;
    private static final Double TOTAL_PRICE = 100.;

    @Before
    public void setUp()
    {
        customerLoyaltyPointsService = new DefaultCustomerLoyaltyPointsService();
        customerLoyaltyPointsService.setLoyaltyPointsConfigurationDAO(loyaltyPointsConfigurationDAO);
        customerLoyaltyPointsService.setModelService(modelService);

        customerModel = new CustomerModel();

        currencyModel = new CurrencyModel();
        currencyModel.setSymbol(CURRENCY_SYMBOL);
        currencyModel.setIsocode(CURRENCY_ISOCODE);

        orderModel = new OrderModel();
        orderModel.setTotalPrice(TOTAL_PRICE);
        orderModel.setCurrency(currencyModel);

        loyaltyPointsConfigurationModel = new LoyaltyPointsConfigurationModel();
        loyaltyPointsConfigurationModel.setCode(LPCONF_CODE);
        loyaltyPointsConfigurationModel.setCurrency(currencyModel);
        loyaltyPointsConfigurationModel.setValue(LPCONF_VALUE);
        loyaltyPointsConfigurationModel.setConfigurationType(LoyaltyPointsConfigurationType.ABSOLUTE);
    }

    @Test
    public void testAddLoyaltyPointsToCustomer(){
        when(loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurationByCurrency(currencyModel)).thenReturn(Collections.singletonList(loyaltyPointsConfigurationModel));
        customerLoyaltyPointsService.addLoyaltyPointsToCustomer(customerModel, orderModel);
        Mockito.verify(modelService).save(argCaptor.capture());
        assertEquals(customerModel.getLoyaltyPoints(), argCaptor.getValue().getLoyaltyPoints());
    }

}
