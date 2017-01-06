package de.hybris.merchandise.loyaltysystem.commerceservices.order.impl;

import de.hybris.merchandise.loyaltysystem.model.LoyaltyPointsPaymentInfoModel;
import de.hybris.platform.commerceservices.order.CommercePaymentAuthorizationStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.strategies.GenerateMerchantTransactionCodeStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;

/**
 * Created by wladek on 12/22/16.
 */
public class LoyaltyPointsCommercePaymentAuthorizationStrategy implements CommercePaymentAuthorizationStrategy {
    private GenerateMerchantTransactionCodeStrategy generateMerchantTransactionCodeStrategy;
    private I18NService i18nService;
    private PaymentService paymentService;
    private ModelService modelService;

    @Override
    public PaymentTransactionEntryModel authorizePaymentAmount(CommerceCheckoutParameter parameter) {
        final CartModel cartModel = parameter.getCart();
        final BigDecimal amount = parameter.getAuthorizationAmount();

        PaymentTransactionEntryModel transactionEntryModel = null;
        try
        {
            final PaymentInfoModel paymentInfo = cartModel.getPaymentInfo();
            if (paymentInfo instanceof LoyaltyPointsPaymentInfoModel)
            {
                final Currency currency = getI18nService().getBestMatchingJavaCurrency(cartModel.getCurrency().getIsocode());
                final String merchantTransactionCode = getGenerateMerchantTransactionCodeStrategy().generateCode(cartModel);
                transactionEntryModel = getPaymentService().authorize(merchantTransactionCode, amount, currency,
                        cartModel.getDeliveryAddress(), "");  //TODO: check
                if (transactionEntryModel != null)
                {
                    final PaymentTransactionModel paymentTransaction = transactionEntryModel.getPaymentTransaction();

                    if (TransactionStatus.ACCEPTED.name().equals(transactionEntryModel.getTransactionStatus())
                            || TransactionStatus.REVIEW.name().equals(transactionEntryModel.getTransactionStatus()))
                    {
                        cartModel.setIsPaidByLoyaltyPoints(true);
                        paymentTransaction.setOrder(cartModel);
                        paymentTransaction.setInfo(paymentInfo);
                        getModelService().saveAll(cartModel, paymentTransaction);
                    }
                    else
                    {
                        // TransactionStatus is error or reject remove the PaymentTransaction and TransactionEntry
                        getModelService().removeAll(Arrays.asList(paymentTransaction, transactionEntryModel));
                    }
                }
            }
            return transactionEntryModel;
        } finally {

        }
    }


    public void setGenerateMerchantTransactionCodeStrategy(GenerateMerchantTransactionCodeStrategy generateMerchantTransactionCodeStrategy) {
        this.generateMerchantTransactionCodeStrategy = generateMerchantTransactionCodeStrategy;
    }

    public void setI18nService(I18NService i18nService) {
        this.i18nService = i18nService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public GenerateMerchantTransactionCodeStrategy getGenerateMerchantTransactionCodeStrategy() {
        return generateMerchantTransactionCodeStrategy;
    }

    public I18NService getI18nService() {
        return i18nService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
