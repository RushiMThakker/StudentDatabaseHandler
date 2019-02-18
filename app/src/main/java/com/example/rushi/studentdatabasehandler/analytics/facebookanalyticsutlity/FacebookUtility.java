/*
package com.gosnowapp.utility.analytics.facebookanalyticsutlity;

import android.os.Bundle;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.gosnowapp.model.ResortExperience;
import com.gosnowapp.model.ResortExperiencePackage;
import com.gosnowapp.model.User;
import com.gosnowapp.utility.analytics.AnalyticsConstants;
import com.gosnowapp.utility.analytics.UserPropertiesConstants;

import static com.gosnowapp.App.getFacebookAnalyticsInstance;

*/
/**
 * Facebook logger Utility class to support logging events and adding user property when
 * necessary. This will get firebase instance from app class and performs necessary actions.
 *//*

public class FacebookUtility
{
  */
/**
   * Logs user property after sign in.
   *
   * @param user User object received after sign in
   *//*

  public void logUserProperty(User user)
  {
    AppEventsLogger logger = getFacebookAnalyticsInstance();
    Bundle params = new Bundle();
    params.putString(UserPropertiesConstants.riderTypeId, user.getRiderTypeId());
    params.putString(UserPropertiesConstants.riderTypeName, user.getRiderTypeName());
    params.putString(UserPropertiesConstants.skillLevelId, user.getSkillLevelId());
    params.putString(UserPropertiesConstants.skillLevelName, user.getSkillLevelName());
    params.putString(UserPropertiesConstants.latitude, user.getLatitude());
    params.putString(UserPropertiesConstants.longitude, user.getLongitude());
    params.putString(UserPropertiesConstants.gender, user.getGender());
    params.putString(UserPropertiesConstants.age, user.getAge());
    params.putString(UserPropertiesConstants.preferedInterestId, user.getPreferedInterestId());
    AppEventsLogger.updateUserProperties(params, null);
  }

  */
/**
   * Logs login method of user. Uses {@link AppEventsConstants} to define login event
   * with parameter method name specified in argument
   *
   * @param loginMethod login method used to login
   *//*

  public void logLoginEvent(String loginMethod)
  {
    AppEventsLogger logger = getFacebookAnalyticsInstance();
    Bundle params = new Bundle();
    params.putString(AnalyticsConstants.CommonConstants.Param.METHOD, loginMethod);
    logger.logEvent(AnalyticsConstants.FacebookConstants.Event.LOGIN, params);
  }

  public void logLogoutEvent(String logoutTime, String timeUserWasOnline)
  {
    AppEventsLogger logger = getFacebookAnalyticsInstance();
    Bundle params = new Bundle();
    params.putString(AnalyticsConstants.CommonConstants.Param.LOGOUT_TIME, logoutTime);
    params.putString(AnalyticsConstants.CommonConstants.Param.ONLINE_TIME, timeUserWasOnline);
    logger.logEvent(AnalyticsConstants.CommonConstants.Event.LOGOUT, params);
  }

  */
/**
   * Logs an event when user opens experience list. This event could be the starting point of the
   * funnel to make booking.
   *//*

  public void logExperienceListOpenedEvent()
  {
    AppEventsLogger logger = getFacebookAnalyticsInstance();
    Bundle params = new Bundle();
    params.putString(AnalyticsConstants.FacebookConstants.Param.ITEM_CATEGORY, "experiences");
    logger.logEvent(AnalyticsConstants.FacebookConstants.Event.VIEW_ITEM_LIST, params);
  }

  */
/**
   * Logs an event when user opens experience details screen. This event could be the second
   * point of the funnel to make booking.
   *//*

  public void logResortExperienceDetailsOpenedEvent(ResortExperience selectedResExperience,
      String programQuantity, String packageQuantity, String programIds, String reviewCount)
  {
    AppEventsLogger logger = getFacebookAnalyticsInstance();
    Bundle params = new Bundle();
    params.putString(AnalyticsConstants.FacebookConstants.Param.ITEM_ID, selectedResExperience.getId());
    params.putString(AnalyticsConstants.FacebookConstants.Param.ITEM_NAME, selectedResExperience.getExpName());
    params.putString(AnalyticsConstants.FacebookConstants.Param.ITEM_CATEGORY, "experiences");
    params.putString(AnalyticsConstants.CommonConstants.Param.RESORT_ID,selectedResExperience.getResortId());
    params.putString(AnalyticsConstants.CommonConstants.Param.RESORT_VALIDATION_TIME,selectedResExperience.getResortBookValidationTime());
    params.putString(AnalyticsConstants.CommonConstants.Param.IS_DISABLED,selectedResExperience.getIsDisabled());
    params.putString(AnalyticsConstants.CommonConstants.Param.EXP_CAT,selectedResExperience.getExpCat());
    params.putString(AnalyticsConstants.CommonConstants.Param.EXP_SUB_CAT,selectedResExperience.getExpSubCat());
    params.putString(AnalyticsConstants.CommonConstants.Param.RATING,selectedResExperience.getRating());
    params.putString(AnalyticsConstants.CommonConstants.Param.PROGRAM_QUANTITY,programQuantity);
    params.putString(AnalyticsConstants.CommonConstants.Param.PROGRAM_IDS,programIds);
    params.putString(AnalyticsConstants.CommonConstants.Param.PACKAGE_QUANTITY,packageQuantity);
    params.putString(AnalyticsConstants.CommonConstants.Param.REVIEW_COUNT,reviewCount);
    logger.logEvent(AnalyticsConstants.FacebookConstants.Event.VIEW_ITEM, params);
  }

  */
/**
   * Logs an event when user selects experience package and schedule dates. This event could be the
   * third point of the funnel to make booking.
   *//*

  public void logSelectExperiencePackageEvent(ResortExperiencePackage selectedResortExpPackage,
      String scheduledDates)
  {
    AppEventsLogger logger = getFacebookAnalyticsInstance();
    Bundle params = new Bundle();
    params.putString(AnalyticsConstants.CommonConstants.Param.PACKAGE_ID,selectedResortExpPackage.getPackageId());
    params.putString(AnalyticsConstants.CommonConstants.Param.PROGRAM_ID,selectedResortExpPackage.getProgramId());
    params.putString(AnalyticsConstants.CommonConstants.Param.PACKAGE_NAME,selectedResortExpPackage.getPackageName());
    params.putString(AnalyticsConstants.CommonConstants.Param.PACKAGE_RATE,selectedResortExpPackage.getPackageRate());
    params.putString(AnalyticsConstants.CommonConstants.Param.PACKAGE_GUEST_RATE,selectedResortExpPackage.getPackageGuestRate());
    params.putString(AnalyticsConstants.CommonConstants.Param.PACKAGE_START_DATETIME,selectedResortExpPackage.getPackageStartDateTime());
    params.putString(AnalyticsConstants.CommonConstants.Param.PACKAGE_END_DATETIME,selectedResortExpPackage.getPackageEndDateTime());
    params.putString(AnalyticsConstants.CommonConstants.Param.PACKAGE_TYPE,selectedResortExpPackage.getPackageType());
    params.putString(AnalyticsConstants.CommonConstants.Param.PACK_DAYS,selectedResortExpPackage.getPackDays());
    params.putString(AnalyticsConstants.CommonConstants.Param.PACK_START_TIME,selectedResortExpPackage.getPackStartTime());
    params.putString(AnalyticsConstants.CommonConstants.Param.PACK_END_TIME,selectedResortExpPackage.getPackEndTime());
    params.putString(AnalyticsConstants.CommonConstants.Param.CURRENCY_CODE,selectedResortExpPackage.getCurrencyCode());
    params.putString(AnalyticsConstants.CommonConstants.Param.MAX_PARTICIPANTS_ALLOWED,selectedResortExpPackage.getMaxParticipantsAllowed());
    params.putString(AnalyticsConstants.CommonConstants.Param.FIXED_DATE_ARRAY,selectedResortExpPackage.getFixedDateArray());
    params.putString(AnalyticsConstants.CommonConstants.Param.SCHEDULED_DATES,scheduledDates);
    logger.logEvent(AnalyticsConstants.CommonConstants.Event.SELECT_EXPERIENCE_PACKAGE, params);
  }

  */
/**
   * Logs an event when user clicks on finishes booking and client token is generated for that
   * transaction. This is called before processing client details.
   * This event could be the fourth point of the funnel to make booking.
   *//*

  public void logBeginCheckoutProcessEvent()
  {
    AppEventsLogger logger = getFacebookAnalyticsInstance();
    Bundle params = new Bundle();
    params.putInt(AnalyticsConstants.FacebookConstants.Param.CHECKOUT_STEP,1);
    logger.logEvent(AppEventsConstants.EVENT_NAME_INITIATED_CHECKOUT, params);
  }

  */
/**
   * Logs an event when there is a successful response of adding resort experience. This steps generates booking id and
   * that would be used to generate transaction id in the last step of checkout by calling createpaymentmethodrequest.
   * This is called after successful response from braintree after processing card details.
   * This event could be the fifth point of the funnel to make booking.
   *//*

  public void logCheckoutProgressBookingAddedEvent(String paymentMethodNonce, String bookingId,
      String totalPrice, boolean hasAvailedLiftPass,
      String liftPassPrice, boolean bookingForSelf)
  {
    AppEventsLogger logger = getFacebookAnalyticsInstance();
    Bundle params = new Bundle();
    params.putInt(AnalyticsConstants.FacebookConstants.Param.CHECKOUT_STEP,2);
    params.putString(AnalyticsConstants.CommonConstants.Param.PAYMENTMETHOD_NONCE ,paymentMethodNonce);
    params.putString(AnalyticsConstants.CommonConstants.Param.BOOKING_ID,bookingId);
    params.putString(AnalyticsConstants.CommonConstants.Param.TOTAL_PRICE,totalPrice);
    params.putBoolean(AnalyticsConstants.CommonConstants.Param.HAS_AVAILED_LIFTPASS,hasAvailedLiftPass);
    params.putString(AnalyticsConstants.CommonConstants.Param.LIFTPASS_PRICE,liftPassPrice);
    params.putBoolean(AnalyticsConstants.CommonConstants.Param.BOOKING_FOR_SELF,bookingForSelf);
    logger.logEvent(AnalyticsConstants.FacebookConstants.Event.CHECKOUT_PROGRESS, params);
  }

  */
/**
   * Logs an event when there is a response of creating payment request for resort experience.
   * This steps generates transaction id if transaction is successfule and that would be used by backend to complete payments
   * This event could be the sixth and final point of the funnel to make booking.
   *//*

  public void logCheckoutProgressPaymentRequestGeneratedEvent(boolean isSuccessful,
      String braintreeResponseMessage, String braintreeResponseStatus)
  {
    AppEventsLogger logger = getFacebookAnalyticsInstance();
    Bundle params = new Bundle();
    params.putInt(AnalyticsConstants.FacebookConstants.Param.CHECKOUT_STEP,3);
    params.putBoolean(AnalyticsConstants.CommonConstants.Param.IS_SUCCESS,isSuccessful);
    params.putString(AnalyticsConstants.CommonConstants.Param.BRAINTREE_RESPONSE_MESSAGE,braintreeResponseMessage);
    params.putString(AnalyticsConstants.CommonConstants.Param.BRAINTREE_RESPONSE_STATUS,braintreeResponseStatus);
    logger.logEvent(AnalyticsConstants.FacebookConstants.Event.ECOMMERCE_PURCHASE, params);
  }

  public void setUserId(String userId)
  {
    AppEventsLogger.setUserID(userId);
  }
}
*/
