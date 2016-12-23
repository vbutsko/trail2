package de.hybris.merchandise.loyaltysystem.commerceservices.payment.impl;


import de.hybris.merchandise.loyaltysystem.commerceservices.payment.LoyaltyPointsPaymentService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.request.StandaloneRefundRequest;
import de.hybris.platform.payment.commands.request.SubscriptionAuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.dto.NewSubscription;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * Created by wladek on 12/22/16.
 */
public class LoyaltySystemPaymentServiceImpl implements PaymentService {
    @Resource
    private CommonI18NService commonI18NService;
    @Resource
    private ModelService modelService;
    @Resource
    private LoyaltyPointsPaymentService loyaltyPointsPaymentService;
    @Resource
    private UserService userService;


    public void setCommonI18NService(CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
    /** @deprecated */
    @Deprecated
    @Override
    public PaymentTransactionEntryModel authorize(OrderModel orderModel, CardInfo cardInfo) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel authorize(String merchantTransactionCode, BigDecimal amount, Currency currency, AddressModel deliveryAddress, String subscriptionID) throws AdapterException {
        BillingInfo shippingInfo = this.createBillingInfo(deliveryAddress);
        PaymentTransactionModel transaction = (PaymentTransactionModel)this.modelService.create(PaymentTransactionModel.class);
        transaction.setCode(merchantTransactionCode);
        transaction.setPlannedAmount(amount);
        this.modelService.save(transaction);
        PaymentTransactionType paymentTransactionType = PaymentTransactionType.AUTHORIZATION;
        String newEntryCode = this.getNewPaymentTransactionEntryCode(transaction, paymentTransactionType);

        loyaltyPointsPaymentService.payForOrderByLoyaltyPoints((CustomerModel) userService.getCurrentUser(), amount);

        PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel)this.modelService.create(PaymentTransactionEntryModel.class);
        entry.setAmount(amount);
        entry.setType(paymentTransactionType);
        if(currency != null) {
            entry.setCurrency(this.commonI18NService.getCurrency(currency.getCurrencyCode()));
        }
        entry.setTime(new Date());
        entry.setPaymentTransaction(transaction);
        entry.setCode(newEntryCode);
        if(subscriptionID != null) {
            entry.setSubscriptionID(subscriptionID);
        }
        this.modelService.save(entry);
        this.modelService.refresh(transaction);
        return entry;
    }



    private BillingInfo createBillingInfo(AddressModel address) {
        if(address == null) {
            return null;
        } else {
            BillingInfo billingInfo = new BillingInfo();
            billingInfo.setCity(address.getTown());
            if(address.getCountry() != null) {
                billingInfo.setCountry(address.getCountry().getIsocode());
            }

            billingInfo.setEmail(address.getEmail());
            billingInfo.setFirstName(address.getFirstname());
            billingInfo.setLastName(address.getLastname());
            billingInfo.setPhoneNumber(address.getPhone1());
            billingInfo.setPostalCode(address.getPostalcode());
            if(address.getRegion() != null) {
                billingInfo.setState(address.getRegion().getName());
            }

            billingInfo.setStreet1(address.getStreetname());
            billingInfo.setStreet2(address.getStreetnumber());
            return billingInfo;
        }
    }

    @Override
    public PaymentTransactionEntryModel authorize(String s, BigDecimal bigDecimal, Currency currency, AddressModel addressModel, String s1, String s2, String s3) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel authorize(String s, BigDecimal bigDecimal, Currency currency, AddressModel addressModel, AddressModel addressModel1, CardInfo cardInfo) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel authorize(PaymentTransactionModel paymentTransactionModel, BigDecimal bigDecimal, Currency currency, AddressModel addressModel, String s, String s1) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel authorize(PaymentTransactionModel paymentTransactionModel, BigDecimal bigDecimal, Currency currency, AddressModel addressModel, String s) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel authorize(PaymentTransactionModel paymentTransactionModel, BigDecimal bigDecimal, Currency currency, AddressModel addressModel, AddressModel addressModel1, CardInfo cardInfo) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel capture(PaymentTransactionModel paymentTransactionModel) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel cancel(PaymentTransactionEntryModel paymentTransactionEntryModel) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel refundFollowOn(PaymentTransactionModel paymentTransactionModel, BigDecimal bigDecimal) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel refundStandalone(StandaloneRefundRequest standaloneRefundRequest) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel refundStandalone(String s, BigDecimal bigDecimal, Currency currency, AddressModel addressModel, CardInfo cardInfo) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel partialCapture(PaymentTransactionModel paymentTransactionModel, BigDecimal bigDecimal) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionModel getPaymentTransaction(String s) {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel getPaymentTransactionEntry(String s) {
        throw new NotImplementedException();
    }

    @Override
    public void attachPaymentInfo(PaymentTransactionModel paymentTransactionModel, UserModel userModel, CardInfo cardInfo, BigDecimal bigDecimal) {
        throw new NotImplementedException();
    }

    @Override
    public NewSubscription createSubscription(PaymentTransactionModel paymentTransactionModel, AddressModel addressModel, CardInfo cardInfo) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public NewSubscription createSubscription(String s, String s1, Currency currency, AddressModel addressModel, CardInfo cardInfo) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel updateSubscription(String s, String s1, String s2, AddressModel addressModel, CardInfo cardInfo) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel getSubscriptionData(String s, String s1, String s2, BillingInfo billingInfo, CardInfo cardInfo) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public PaymentTransactionEntryModel deleteSubscription(String s, String s1, String s2) throws AdapterException {
        throw new NotImplementedException();
    }

    @Override
    public String getNewPaymentTransactionEntryCode(PaymentTransactionModel paymentTransactionModel, PaymentTransactionType paymentTransactionType) {
        throw new NotImplementedException();
    }

    public void setLoyaltyPointsPaymentService(LoyaltyPointsPaymentService loyaltyPointsPaymentService) {
        this.loyaltyPointsPaymentService = loyaltyPointsPaymentService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
