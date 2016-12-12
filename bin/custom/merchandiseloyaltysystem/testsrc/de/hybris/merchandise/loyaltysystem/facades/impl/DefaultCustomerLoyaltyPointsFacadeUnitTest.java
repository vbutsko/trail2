package de.hybris.merchandise.loyaltysystem.facades.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.merchandise.loyaltysystem.core.services.impl.DefaultCustomerLoyaltyPointsService;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultCustomerLoyaltyPointsFacadeUnitTest {

    @InjectMocks
    private DefaultCustomerLoyaltyPointsFacade defaultCustomerLoyaltyPointsFacade;
    @Mock
    private CustomerModel customerModel;
    @Mock
    private OrderModel orderModel;
    @Mock
    private DefaultAcceleratorCheckoutFacade defaultAcceleratorCheckoutFacade;
    @Mock
    private CartModel cartModel;



    public void testPlaceOrder(){

        //defaultCustomerLoyaltyPointsFacade.
    }


}
