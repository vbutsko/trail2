/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 6, 2017 12:48:24 PM                     ---
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
import de.hybris.merchandise.loyaltysystem.jalo.LoyaltyPointsConfiguration;
import de.hybris.merchandise.loyaltysystem.jalo.LoyaltyPointsPaymentInfo;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.User;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>MerchandiseloyaltysystemManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMerchandiseloyaltysystemManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("loyaltyPoints", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.user.Customer", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("isPaidByLoyaltyPoints", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrder", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public LoyaltyPointsConfiguration createLoyaltyPointsConfiguration(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MerchandiseloyaltysystemConstants.TC.LOYALTYPOINTSCONFIGURATION );
			return (LoyaltyPointsConfiguration)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating LoyaltyPointsConfiguration : "+e.getMessage(), 0 );
		}
	}
	
	public LoyaltyPointsConfiguration createLoyaltyPointsConfiguration(final Map attributeValues)
	{
		return createLoyaltyPointsConfiguration( getSession().getSessionContext(), attributeValues );
	}
	
	public LoyaltyPointsPaymentInfo createLoyaltyPointsPaymentInfo(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MerchandiseloyaltysystemConstants.TC.LOYALTYPOINTSPAYMENTINFO );
			return (LoyaltyPointsPaymentInfo)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating LoyaltyPointsPaymentInfo : "+e.getMessage(), 0 );
		}
	}
	
	public LoyaltyPointsPaymentInfo createLoyaltyPointsPaymentInfo(final Map attributeValues)
	{
		return createLoyaltyPointsPaymentInfo( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return MerchandiseloyaltysystemConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.isPaidByLoyaltyPoints</code> attribute.
	 * @return the isPaidByLoyaltyPoints
	 */
	public Boolean isIsPaidByLoyaltyPoints(final SessionContext ctx, final AbstractOrder item)
	{
		return (Boolean)item.getProperty( ctx, MerchandiseloyaltysystemConstants.Attributes.AbstractOrder.ISPAIDBYLOYALTYPOINTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.isPaidByLoyaltyPoints</code> attribute.
	 * @return the isPaidByLoyaltyPoints
	 */
	public Boolean isIsPaidByLoyaltyPoints(final AbstractOrder item)
	{
		return isIsPaidByLoyaltyPoints( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.isPaidByLoyaltyPoints</code> attribute. 
	 * @return the isPaidByLoyaltyPoints
	 */
	public boolean isIsPaidByLoyaltyPointsAsPrimitive(final SessionContext ctx, final AbstractOrder item)
	{
		Boolean value = isIsPaidByLoyaltyPoints( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.isPaidByLoyaltyPoints</code> attribute. 
	 * @return the isPaidByLoyaltyPoints
	 */
	public boolean isIsPaidByLoyaltyPointsAsPrimitive(final AbstractOrder item)
	{
		return isIsPaidByLoyaltyPointsAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.isPaidByLoyaltyPoints</code> attribute. 
	 * @param value the isPaidByLoyaltyPoints
	 */
	public void setIsPaidByLoyaltyPoints(final SessionContext ctx, final AbstractOrder item, final Boolean value)
	{
		item.setProperty(ctx, MerchandiseloyaltysystemConstants.Attributes.AbstractOrder.ISPAIDBYLOYALTYPOINTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.isPaidByLoyaltyPoints</code> attribute. 
	 * @param value the isPaidByLoyaltyPoints
	 */
	public void setIsPaidByLoyaltyPoints(final AbstractOrder item, final Boolean value)
	{
		setIsPaidByLoyaltyPoints( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.isPaidByLoyaltyPoints</code> attribute. 
	 * @param value the isPaidByLoyaltyPoints
	 */
	public void setIsPaidByLoyaltyPoints(final SessionContext ctx, final AbstractOrder item, final boolean value)
	{
		setIsPaidByLoyaltyPoints( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.isPaidByLoyaltyPoints</code> attribute. 
	 * @param value the isPaidByLoyaltyPoints
	 */
	public void setIsPaidByLoyaltyPoints(final AbstractOrder item, final boolean value)
	{
		setIsPaidByLoyaltyPoints( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.loyaltyPoints</code> attribute.
	 * @return the loyaltyPoints - defines how much customer have loyalty points
	 */
	public Double getLoyaltyPoints(final SessionContext ctx, final Customer item)
	{
		return (Double)item.getProperty( ctx, MerchandiseloyaltysystemConstants.Attributes.Customer.LOYALTYPOINTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.loyaltyPoints</code> attribute.
	 * @return the loyaltyPoints - defines how much customer have loyalty points
	 */
	public Double getLoyaltyPoints(final Customer item)
	{
		return getLoyaltyPoints( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.loyaltyPoints</code> attribute. 
	 * @return the loyaltyPoints - defines how much customer have loyalty points
	 */
	public double getLoyaltyPointsAsPrimitive(final SessionContext ctx, final Customer item)
	{
		Double value = getLoyaltyPoints( ctx,item );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.loyaltyPoints</code> attribute. 
	 * @return the loyaltyPoints - defines how much customer have loyalty points
	 */
	public double getLoyaltyPointsAsPrimitive(final Customer item)
	{
		return getLoyaltyPointsAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.loyaltyPoints</code> attribute. 
	 * @param value the loyaltyPoints - defines how much customer have loyalty points
	 */
	public void setLoyaltyPoints(final SessionContext ctx, final Customer item, final Double value)
	{
		item.setProperty(ctx, MerchandiseloyaltysystemConstants.Attributes.Customer.LOYALTYPOINTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.loyaltyPoints</code> attribute. 
	 * @param value the loyaltyPoints - defines how much customer have loyalty points
	 */
	public void setLoyaltyPoints(final Customer item, final Double value)
	{
		setLoyaltyPoints( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.loyaltyPoints</code> attribute. 
	 * @param value the loyaltyPoints - defines how much customer have loyalty points
	 */
	public void setLoyaltyPoints(final SessionContext ctx, final Customer item, final double value)
	{
		setLoyaltyPoints( ctx, item, Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.loyaltyPoints</code> attribute. 
	 * @param value the loyaltyPoints - defines how much customer have loyalty points
	 */
	public void setLoyaltyPoints(final Customer item, final double value)
	{
		setLoyaltyPoints( getSession().getSessionContext(), item, value );
	}
	
}
