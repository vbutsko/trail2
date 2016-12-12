package de.hybris.merchandise.loyaltysystem.core.daos.impl;


import de.hybris.merchandise.loyaltysystem.enums.LoyaltyPointsConfigurationType;
import de.hybris.merchandise.loyaltysystem.core.daos.LoyaltyPointsConfigurationDAO;
import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsConfigurationModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


//@IntegrationTest
public class DefaultLoyaltyPointsConfigurationDAOIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private LoyaltyPointsConfigurationDAO loyaltyPointsConfigurationDAO;

    @Resource
    private ModelService modelService;

    private static final String LPCONF_CODE = "testname";

    private static final Double LPCONF_VALUE = 0.1;

    private static final String CURRENCY_SYMBOL = "testsymbol";
    private static final String CURRENCY_ISOCODE = "testisocode";

    @Test
    public void loyaltyPointsConfigurationDAOTest(){
        CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setSymbol(CURRENCY_SYMBOL);
        currencyModel.setIsocode(CURRENCY_ISOCODE);
        List<LoyaltyPointsConfigurationModel> configsByCode = loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurationByCode(LPCONF_CODE);
        assertTrue("No config should be returned", configsByCode.isEmpty());

        List<LoyaltyPointsConfigurationModel> allConfigs = loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurations();
        final int size = allConfigs.size();

        final LoyaltyPointsConfigurationModel loyaltyPointsConfigurationModel = new LoyaltyPointsConfigurationModel();
        loyaltyPointsConfigurationModel.setCode(LPCONF_CODE);
        loyaltyPointsConfigurationModel.setCurrency(currencyModel);
        loyaltyPointsConfigurationModel.setValue(LPCONF_VALUE);
        loyaltyPointsConfigurationModel.setConfigurationType(LoyaltyPointsConfigurationType.ABSOLUTE);
        modelService.save(loyaltyPointsConfigurationModel);

        allConfigs = loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurations();
        assertEquals(size + 1, allConfigs.size());
        assertEquals("Unexpected config found", loyaltyPointsConfigurationModel, allConfigs.get(allConfigs.size() - 1));

        configsByCode = loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurationByCode(LPCONF_CODE);
        assertEquals("Did not find the Config we just saved", 1, configsByCode.size());
        assertEquals("Retrieved config's code attribute incorrect", LPCONF_CODE, configsByCode.get(0).getCode());
        assertEquals("Retrieved config's value attribute incorrect", LPCONF_VALUE, configsByCode.get(0).getValue());
        assertEquals("Retrieved config's configurationType attribite incorrect", LoyaltyPointsConfigurationType.ABSOLUTE, configsByCode.get(0).getConfigurationType());
        assertEquals("Retrieved config's currency attribute incorrect", currencyModel, configsByCode.get(0).getCurrency());

        List<LoyaltyPointsConfigurationModel> configsByCurrency = loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurationByCurrency(currencyModel);
        for(LoyaltyPointsConfigurationModel config: configsByCurrency){
            assertEquals("Retrieved config's currency attribute incorrect", currencyModel, config.getCurrency());
            assertEquals("Retrieved config's symbol attribute in currency incorrect", CURRENCY_SYMBOL, config.getCurrency().getSymbol());
            assertEquals("Retrieved config's isocode attribute in currency incorrect", CURRENCY_ISOCODE, config.getCurrency().getIsocode());
            assertEquals("Retrieved config's configurationType attribite incorrect", LoyaltyPointsConfigurationType.ABSOLUTE, config.getConfigurationType());
        }
    }

    @Test
    public void testFindLoyaltyPointsConfigurations_EmptyStringParam()
    {
        //calling findStadiumsByCode() with an empty String - returns no results
        final List<LoyaltyPointsConfigurationModel> loyaltyPointsConfigurationByCode = loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurationByCode("");
        assertTrue("No Stadium should be returned", loyaltyPointsConfigurationByCode.isEmpty());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testFindLoyaltyPointsConfigurations_NullParam()
    {
        //calling findStadiumByCode with null should throw an IllegalArgumentException
        loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurationByCode(null); //method's return value not captured
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindLoyaltyPointsConfigurationByCurrency(){
        loyaltyPointsConfigurationDAO.findLoyaltyPointsConfigurationByCurrency(null);
    }

}
