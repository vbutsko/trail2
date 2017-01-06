package de.hybris.merchandise.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.SopPaymentDetailsForm;

/**
 * Created by wladek on 12/29/16.
 */
public class LoyaltyPointsSopPaymentDetailsForm extends SopPaymentDetailsForm {

    private String payForOrderBy;

    public String getPayForOrderBy() {
        return payForOrderBy;
    }

    public void setPayForOrderBy(String payForOrderBy) {
        this.payForOrderBy = payForOrderBy;
    }
}
