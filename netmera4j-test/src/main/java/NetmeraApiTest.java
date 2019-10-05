import netmera4j.Netmera;
import netmera4j.NetmeraApi;
import netmera4j.callback.NetmeraCallBack;
import netmera4j.constant.Platform;
import netmera4j.model.Category;
import netmera4j.model.NewDevice;
import netmera4j.model.UserAndProfileAttributeList;
import netmera4j.model.UserAndProfileAttributeMap;
import netmera4j.request.*;
import netmera4j.response.GetDeviceTokensResponse;
import netmera4j.response.GetProfileAttributesResponse;
import netmera4j.response.GetUserDevicesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Murat Karagözgil
 */
public class NetmeraApiTest {

    private Logger logger = LoggerFactory.getLogger(NetmeraApiTest.class);

    private Netmera netmeraApi = NetmeraApi.Build(TARGET_HOST, API_KEY);

    private static final String EXTERNAL_ID = "murat1";
    private static final String TOKEN = "token1";
    private static final String API_KEY = "N79vhZlSKZD5upipyh7NdHIgPOIassVYRRAdPjga2sv00ORK4DHaBg";
    private static final String TARGET_HOST = "http://nova.sdpaas.com";

    private static final String TAG_NAME = "NETMERA4J_TAG";
    private static final List<String> EXTERNAL_ID_LIST = Arrays.asList("murat1", "murat2", "murat3");

    private static final List<NewDevice> NEW_DEVICE_LIST = Arrays.asList(NewDevice.builder().deviceToken("token1").platform(Platform.ANDROID).extId(EXTERNAL_ID_LIST.get(0)).build(), NewDevice.builder().deviceToken("token2").platform(Platform.ANDROID).extId(EXTERNAL_ID_LIST.get(0)).build());

    private static final String ARRAY_PROFILE_ATTRIBUTE_NAME = "lastVisitedCategories";
    private static final String ARRAY_PROFILE_ATTRIBUTE_NAME_2 = "lastVisitedProduct";
    private static final String PROFILE_ATTRIBUTE_NAME = "name";
    private static final String PROFILE_ATTRIBUTE_VALUE = "murat";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NetmeraApiTest netmeraApiTest = new NetmeraApiTest();

        // DEVICE
        CompletableFuture completableFuture = new CompletableFuture();
        netmeraApiTest.testCreateNewDevicesRequest(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testGetUserDevicesRequest(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testGetDeviceTokens(completableFuture);
        completableFuture.get();

        // TAG
        completableFuture = new CompletableFuture();
        netmeraApiTest.testAddTagToUsersRequest(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testRemoveTagToUsersRequest(completableFuture);
        completableFuture.get();

        // PROFILE ATTRIBUTE
        completableFuture = new CompletableFuture();
        netmeraApiTest.setProfileAttributeToUser(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.getProfileAttributeAUser(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testDeleteProfileAttributeFromAllUsers(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testPushProfileAttributesToUser(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testPullProfileAttributesFromUser(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testUnsetProfileAttributes(completableFuture);
        completableFuture.get();

        // CATEGORY
        completableFuture = new CompletableFuture();
        netmeraApiTest.testSetCategoryPreference(completableFuture);
        completableFuture.get();

        // PUSH PREFERENCES
        completableFuture = new CompletableFuture();
        netmeraApiTest.testDisablePushWithDeviceToken(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testDisablePushWithExternalId(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testEnablePushWithDeviceToken(completableFuture);
        completableFuture.get();

        completableFuture = new CompletableFuture();
        netmeraApiTest.testEnablePushWithExternalId(completableFuture);
        completableFuture.get();
    }

    public void testCreateNewDevicesRequest(CompletableFuture completableFuture) {
        Netmera netmeraApi = NetmeraApi.Build(TARGET_HOST, API_KEY);
        netmeraApi.sendRequest(new AddNewDevicesRequest(NEW_DEVICE_LIST), getStandardCallBack(completableFuture));
    }

    private void testGetUserDevicesRequest(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(GetUserDevicesRequest.Builder(EXTERNAL_ID).build(), new NetmeraCallBack<GetUserDevicesResponse>() {

            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(httpStatus);
            }

            @Override
            protected void handleResponseData(GetUserDevicesResponse data) {
            }

            @Override
            protected void handleError(Response<GetUserDevicesResponse> response) {
            }

            @Override
            protected void handleException(Exception t) {
                t.printStackTrace();
            }
        });
    }

    private void testGetDeviceTokens(CompletableFuture completableFuture) {
        GetDeviceTokensRequest getDeviceTokensRequest = new GetDeviceTokensRequest();
        netmeraApi.sendRequest(getDeviceTokensRequest, new NetmeraCallBack<GetDeviceTokensResponse>() {

            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(httpStatus);
            }

            @Override
            protected void handleResponseData(GetDeviceTokensResponse data) {
                testGetDeviceTokensViaResponse(data);
            }

            @Override
            protected void handleError(Response<GetDeviceTokensResponse> response) {

            }

            @Override
            protected void handleException(Exception t) {

            }
        });
    }

    private void testGetDeviceTokensViaResponse(GetDeviceTokensResponse response) {
        netmeraApi.sendRequest(response, new NetmeraCallBack<GetDeviceTokensResponse>() {

            @Override
            protected void handleResponseData(GetDeviceTokensResponse data) {
            }

            @Override
            protected void handleError(Response<GetDeviceTokensResponse> response) {
            }

            @Override
            protected void handleException(Exception t) {
                t.printStackTrace();
            }
        });
    }


    private void testAddTagToUsersRequest(CompletableFuture completableFuture) {
        AddTagToUsersRequest addTagToUsersRequest = new AddTagToUsersRequest(TAG_NAME, EXTERNAL_ID_LIST);
        netmeraApi.sendRequest(addTagToUsersRequest, getStandardCallBack(completableFuture));
    }

    private void testRemoveTagToUsersRequest(CompletableFuture completableFuture) {
        RemoveTagFromUsersRequest removeTagFromUsersRequest = new RemoveTagFromUsersRequest(TAG_NAME, EXTERNAL_ID_LIST);
        netmeraApi.sendRequest(removeTagFromUsersRequest, getStandardCallBack(completableFuture));
    }

    private void setProfileAttributeToUser(CompletableFuture completableFuture) {
        AddProfileAttributeRequest addProfileAttributeRequest = new AddProfileAttributeRequest();
        UserAndProfileAttributeMap userAndProfileAttributeMap = new UserAndProfileAttributeMap();
        userAndProfileAttributeMap.setExtId(EXTERNAL_ID);
        userAndProfileAttributeMap.addProfileAttribute(PROFILE_ATTRIBUTE_NAME, PROFILE_ATTRIBUTE_VALUE);
        userAndProfileAttributeMap.addProfileAttribute("UUID", UUID.randomUUID().toString());
        addProfileAttributeRequest.setUserAndProfileAttributeMaps(Collections.singletonList(userAndProfileAttributeMap));
        netmeraApi.sendRequest(addProfileAttributeRequest, getStandardCallBack(completableFuture));
    }

    private void getProfileAttributeAUser(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(new GetProfileAttributesRequest(EXTERNAL_ID), new NetmeraCallBack<GetProfileAttributesResponse>() {

            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(httpStatus);
            }

            @Override
            protected void handleResponseData(GetProfileAttributesResponse data) {

            }

            @Override
            protected void handleError(Response<GetProfileAttributesResponse> response) {

            }

            @Override
            protected void handleException(Exception t) {

            }
        });
    }

    private void testSetCategoryPreference(CompletableFuture completableFuture) {
        SetCategoryPreferenceRequest setCategoryPreferenceRequest = new SetCategoryPreferenceRequest().addCategory(Category.builder().extId(EXTERNAL_ID).category("elbise").enable(true).build());
        netmeraApi.sendRequest(setCategoryPreferenceRequest, getStandardCallBack(completableFuture));
    }

    private void testUnsetProfileAttributes(CompletableFuture completableFuture) {
        UnsetProfileAttributesRequest unsetProfileAttributesRequest = new UnsetProfileAttributesRequest();
        List<UserAndProfileAttributeList> userAndProfileAttributeLists = new ArrayList<>();
        userAndProfileAttributeLists.add(UserAndProfileAttributeList.builder().extId(EXTERNAL_ID).build().addProfileAttributeArray(PROFILE_ATTRIBUTE_NAME, Collections.singletonList(PROFILE_ATTRIBUTE_VALUE)));
        unsetProfileAttributesRequest.setUserAndProfileAttributeLists(userAndProfileAttributeLists);
        netmeraApi.sendRequest(unsetProfileAttributesRequest, getStandardCallBack(completableFuture));
    }

    private void testDeleteProfileAttributeFromAllUsers(CompletableFuture completableFuture) {
        DeleteProfileAttributeFromAllUsersRequest deleteProfileAttributeFromAllUsersRequest = new DeleteProfileAttributeFromAllUsersRequest(PROFILE_ATTRIBUTE_NAME, PROFILE_ATTRIBUTE_VALUE);
        netmeraApi.sendRequest(deleteProfileAttributeFromAllUsersRequest, getStandardCallBack(completableFuture));
    }

    private void testPullProfileAttributesFromUser(CompletableFuture completableFuture) {
        PullProfileAttributesFromUserRequest pullProfileAttributesFromUserRequest = new PullProfileAttributesFromUserRequest();
        UserAndProfileAttributeList userAndProfileAttributeList = new UserAndProfileAttributeList();
        userAndProfileAttributeList.setExtId(EXTERNAL_ID);
        userAndProfileAttributeList.addProfileAttributeArray(ARRAY_PROFILE_ATTRIBUTE_NAME, Arrays.asList("promotion", "skirt"));
        UserAndProfileAttributeList userAndProfileAttributeList2 = new UserAndProfileAttributeList();
        userAndProfileAttributeList2.setExtId(EXTERNAL_ID);
        userAndProfileAttributeList2.addProfileAttributeArray(ARRAY_PROFILE_ATTRIBUTE_NAME_2, Arrays.asList("promotion", "skirt"));
        pullProfileAttributesFromUserRequest.setUserAndProfileAttributeLists(Arrays.asList(userAndProfileAttributeList, userAndProfileAttributeList2));
        netmeraApi.sendRequest(pullProfileAttributesFromUserRequest, getStandardCallBack(completableFuture));
    }

    private void testPushProfileAttributesToUser(CompletableFuture completableFuture) {
        PushProfileAttributesToUserRequest pushProfileAttributesToUserRequest = new PushProfileAttributesToUserRequest();
        UserAndProfileAttributeList userAndProfileAttributeList = new UserAndProfileAttributeList();
        userAndProfileAttributeList.setExtId(EXTERNAL_ID);
        userAndProfileAttributeList.addProfileAttributeArray(ARRAY_PROFILE_ATTRIBUTE_NAME, Arrays.asList("promotion", "skirt"));
        UserAndProfileAttributeList userAndProfileAttributeList2 = new UserAndProfileAttributeList();
        userAndProfileAttributeList2.setExtId(EXTERNAL_ID);
        userAndProfileAttributeList2.addProfileAttributeArray(ARRAY_PROFILE_ATTRIBUTE_NAME_2, Arrays.asList("promotion", "skirt"));
        pushProfileAttributesToUserRequest.setUserAndProfileAttributeLists(Arrays.asList(userAndProfileAttributeList, userAndProfileAttributeList2));
        netmeraApi.sendRequest(pushProfileAttributesToUserRequest, getStandardCallBack(completableFuture));
    }

    private void testDisablePushWithExternalId(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(DisablePushRequestWithExternalId.builder().extId(EXTERNAL_ID).build(), getStandardCallBack(completableFuture));
    }

    private void testDisablePushWithDeviceToken(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(DisablePushRequestWithToken.builder().deviceToken(TOKEN).build(), getStandardCallBack(completableFuture));
    }

    private void testEnablePushWithExternalId(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(EnablePushRequestWithExternalId.builder().extId(EXTERNAL_ID).build(), getStandardCallBack(completableFuture));
    }

    private void testEnablePushWithDeviceToken(CompletableFuture completableFuture) {
        netmeraApi.sendRequest(EnablePushRequestWithToken.builder().deviceToken(TOKEN).build(), getStandardCallBack(completableFuture));
    }

    private NetmeraCallBack<Void> getStandardCallBack(CompletableFuture completableFuture) {
        return new NetmeraCallBack<Void>() {

            @Override
            protected void handleResponseCode(int httpStatus) {
                logger.info("------------------------------------");
                completableFuture.complete(httpStatus);
            }

            @Override
            protected void handleResponseData(Void data) {
            }

            @Override
            protected void handleError(Response<Void> response) {
            }

            @Override
            protected void handleException(Exception t) {
                t.printStackTrace();
            }
        };
    }
}
