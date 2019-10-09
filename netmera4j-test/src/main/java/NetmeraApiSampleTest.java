import netmera4j.Netmera;
import netmera4j.NetmeraApi;
import netmera4j.callback.NetmeraCallBack;
import netmera4j.request.device.GetUserDevicesRequest;
import netmera4j.response.GetUserDevicesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Murat Karagözgil
 */
public class NetmeraApiSampleTest {

    private Logger logger = LoggerFactory.getLogger(NetmeraApiDeviceTest.class);

    private Netmera netmeraApi = NetmeraApi.Build(TARGET_HOST, API_KEY);

    private static final String EXTERNAL_ID = "murat1";
    private static final String TOKEN = "token1";
    private static final String API_KEY = "N79vhZlSKZD5upipyh7NdHIgPOIassVYRRAdPjga2sv00ORK4DHaBg";
    private static final String TARGET_HOST = "http://nova.sdpaas.com";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NetmeraApiSampleTest netmeraApiDeviceTest = new NetmeraApiSampleTest();

        // DEVICE
        CompletableFuture completableFuture = new CompletableFuture();
        netmeraApiDeviceTest.testGetUserDevicesRequest(completableFuture);
        completableFuture.get();
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
}
