package de.hybris.merchandise.loyaltysystem.commerceservices.payment.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.merchandise.loyaltysystem.core.daos.LoyaltyPointsConfigurationDAO;
import de.hybris.merchandise.loyaltysystem.core.services.impl.DefaultCustomerLoyaltyPointsService;
import de.hybris.merchandise.loyaltysystem.enums.LoyaltyPointsConfigurationType;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsConfigurationModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by wladek on 12/23/16.
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class LoayaltyPointsPaymentServiceImplTest {
    @Mock
    private LoyaltyPointsConfigurationDAO loyaltyPointsConfigurationDAO;
    @Mock
    private ModelService modelService;

    @Captor
    private ArgumentCaptor<Object> argCaptor;


    @InjectMocks
    private LoyaltyPointsPaymentServiceImpl loyaltyPointsPaymentService;

    private CurrencyModel currencyModel;
    private static final String CURRENCY_SYMBOL = "testsymbol";
    private static final String CURRENCY_ISOCODE = "testisocode";

    private CustomerModel customerModel;

    @Before
    public void setUp()
    {
        customerModel = new CustomerModel();
        currencyModel = new CurrencyModel();
        currencyModel.setSymbol(CURRENCY_SYMBOL);
        currencyModel.setIsocode(CURRENCY_ISOCODE);
        loyaltyPointsPaymentService.setLpService(new DefaultCustomerLoyaltyPointsService());
    }
    @Test
    public void testPayForOrderByLoyaltyPoints(){
        customerModel.setLoyaltyPoints(100.);
        loyaltyPointsPaymentService.payForOrderByLoyaltyPoints(customerModel, new BigDecimal(50.));
        Mockito.verify(modelService).save(argCaptor.capture());
        assertEquals(((CustomerModel)argCaptor.getAllValues().get(0)).getLoyaltyPoints(), customerModel.getLoyaltyPoints());

        try {
            loyaltyPointsPaymentService.payForOrderByLoyaltyPoints(customerModel, new BigDecimal(100.));
            assertNotEquals(((CustomerModel) argCaptor.getAllValues().get(0)).getLoyaltyPoints(), customerModel.getLoyaltyPoints());
        }catch(AdapterException ex){
            System.out.println(ex.getMessage());
        }


        loyaltyPointsPaymentService.payForOrderByLoyaltyPoints(customerModel, new BigDecimal(50.));
        Mockito.verify(modelService,Mockito.times(2)).save(argCaptor.capture());
        assertEquals(((CustomerModel)argCaptor.getAllValues().get(1)).getLoyaltyPoints(), customerModel.getLoyaltyPoints());

    }

}
