package netmera4j.service;

import netmera4j.model.device.*;
import netmera4j.request.device.*;
import netmera4j.response.GetDeviceTokensResponse;
import netmera4j.response.GetProfileAttributesResponse;
import netmera4j.response.GetUserDevicesResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
public interface UserService {

    @POST("/rest/3.0/registerUsers")
    Call<Void> createNewDevices(@Body List<NewDevice> deviceList);

    @POST("/rest/3.0/disablePush")
    Call<Void> disablePushWithExternalId(@Body DisablePushRequestWithExternalId disablePushRequestWithExternalId);

    @POST("/rest/3.0/disablePush")
    Call<Void> disablePushWithDeviceToken(@Body DisablePushRequestWithToken disablePushRequestWithToken);

    @POST("/rest/3.0/enablePush")
    Call<Void> enablePushWithExternalId(@Body EnablePushRequestWithExternalId disablePushRequestWithExternalId);

    @POST("/rest/3.0/enablePush")
    Call<Void> enablePushWithDeviceToken(@Body EnablePushRequestWithToken disablePushRequestWithToken);

    @POST("/rest/3.0/tagUsers")
    Call<Void> addTagToUsers(@Body AddTagToUsersRequest addTagToUsersRequest);

    @POST("/rest/3.0/tagUsers")
    Call<Void> removeTagFromUsers(@Body RemoveTagFromUsersRequest removeTagFromUsersRequest);

    @POST("/rest/3.0/setCategoryPreferences")
    Call<Void> setCategoryPreferences(@Body List<Category> categories);

    @POST("/rest/3.0/setProfileAttributes")
    Call<Void> setProfileAttributes(@Body List<UserAndProfileAttributeMap> userAndProfileAttributeMaps);

    @POST("/rest/3.0/unsetProfileAttributes")
    Call<Void> unsetProfileAttributes(@Body List<SingleUnsetObject> userAndProfileAttributeLists);

    @GET("/rest/3.0/getProfileAttributes")
    Call<GetProfileAttributesResponse> getProfileAttributes(@Query("extId") String externalId);

    @POST("/rest/3.0/pushProfileAttributes")
    Call<Void> pushProfileAttributesToUser(@Body List<UserAndProfileAttributeList> userAndProfileAttributeLists);

    @POST("/rest/3.0/pullProfileAttributes")
    Call<Void> pullProfileAttributesToUser(@Body List<UserAndProfileAttributeList> userAndProfileAttributeLists);

    @POST("/rest/3.0/deleteProfileAttributeValue")
    Call<Void> deleteProfileAttributeFromAllUsers(@Body DeleteProfileAttributeFromAllUsersRequest deleteProfileAttributeFromAllUsersRequest);

    @GET("/rest/3.0/getDevices")
    Call<GetUserDevicesResponse> getUserDevices(@Query("extId") String externalId, @Query("pushPermitted") Boolean pushPermitted);

    @GET("/rest/3.0/getDeviceTokens")
    Call<GetDeviceTokensResponse> getDeviceTokens(@Query("max") Integer max, @Query("offset") Integer offSet);

    @GET
    Call<GetDeviceTokensResponse> getDeviceTokens(@Url String url);
}
