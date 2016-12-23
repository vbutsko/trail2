/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Dec 16, 2016 12:43:16 PM                    ---
 * ----------------------------------------------------------------
 *  
 * [y] hybris Platform
 *  
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *  
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *  
 */
package de.hybris.merchandise.loyaltysystem.jalo;

import de.hybris.merchandise.loyaltysystem.constants.MerchandiseloyaltysystemConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem LoyaltyPointsConfiguration}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedLoyaltyPointsConfiguration extends GenericItem
{
	/** Qualifier of the <code>LoyaltyPointsConfiguration.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>LoyaltyPointsConfiguration.configurationType</code> attribute **/
	public static final String CONFIGURATIONTYPE = "configurationType";
	/** Qualifier of the <code>LoyaltyPointsConfiguration.currency</code> attribute **/
	public static final String CURRENCY = "currency";
	/** Qualifier of the <code>LoyaltyPointsConfiguration.value</code> attribute **/
	public static final String VALUE = "value";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(CONFIGURATIONTYPE, AttributeMode.INITIAL);
		tmp.put(CURRENCY, AttributeMode.INITIAL);
		tmp.put(VALUE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.configurationType</code> attribute.
	 * @return the configurationType
	 */
	public EnumerationValue getConfigurationType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, CONFIGURATIONTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.configurationType</code> attribute.
	 * @return the configurationType
	 */
	public EnumerationValue getConfigurationType()
	{
		return getConfigurationType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.configurationType</code> attribute. 
	 * @param value the configurationType
	 */
	public void setConfigurationType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, CONFIGURATIONTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.configurationType</code> attribute. 
	 * @param value the configurationType
	 */
	public void setConfigurationType(final EnumerationValue value)
	{
		setConfigurationType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency(final SessionContext ctx)
	{
		return (Currency)getProperty( ctx, CURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency()
	{
		return getCurrency( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final SessionContext ctx, final Currency value)
	{
		setProperty(ctx, CURRENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final Currency value)
	{
		setCurrency( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.value</code> attribute.
	 * @return the value
	 */
	public Double getValue(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, VALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.value</code> attribute.
	 * @return the value
	 */
	public Double getValue()
	{
		return getValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.value</code> attribute. 
	 * @return the value
	 */
	public double getValueAsPrimitive(final SessionContext ctx)
	{
		Double value = getValue( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>LoyaltyPointsConfiguration.value</code> attribute. 
	 * @return the value
	 */
	public double getValueAsPrimitive()
	{
		return getValueAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.value</code> attribute. 
	 * @param value the value
	 */
	public void setValue(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, VALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.value</code> attribute. 
	 * @param value the value
	 */
	public void setValue(final Double value)
	{
		setValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.value</code> attribute. 
	 * @param value the value
	 */
	public void setValue(final SessionContext ctx, final double value)
	{
		setValue( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>LoyaltyPointsConfiguration.value</code> attribute. 
	 * @param value the value
	 */
	public void setValue(final double value)
	{
		setValue( getSession().getSessionContext(), value );
	}
	
}
