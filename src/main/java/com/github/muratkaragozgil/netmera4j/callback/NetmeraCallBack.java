package com.github.muratkaragozgil.netmera4j.callback;

import lombok.NoArgsConstructor;
import com.github.muratkaragozgil.netmera4j.Netmera;
import com.github.muratkaragozgil.netmera4j.exception.NetmeraError;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author Murat Karagözgil
 */
@NoArgsConstructor
public abstract class NetmeraCallBack<T> implements Callback<T> {

    private Logger logger = LoggerFactory.getLogger(Netmera.class);

    private static int TOTAL_RETRIES = 3;
    private Call<T> call;
    private int retryCount = 0;

    private Converter<ResponseBody, NetmeraError> errorConverter;

    public NetmeraCallBack(int totalRetryCount) {
        TOTAL_RETRIES = totalRetryCount;
    }

    public void setErrorConverter(Converter<ResponseBody, NetmeraError> errorConverter) {
        this.errorConverter = errorConverter;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        this.call = call;
        if (response.code() == 200) {
            logger.info("ResponseData::{}", response.body());
            handleResponseData(response.body());
        } else {
            if (response.errorBody() != null && errorConverter != null) {
                try {
                    NetmeraError error = errorConverter.convert(response.errorBody());
                    logger.error("ResponseError::{}", error);
                } catch (IOException e) {
                }
            } else {
                logger.error("ResponseError::{}", response);
            }

            if (response.code() > 499) {
                retryRequest(call);
            }

            handleError(response);
        }
        logger.info("ResponseCode::{}", response.code());
        handleResponseCode(response.code());
    }

    protected void handleResponseCode(int httpStatus) {

    }

    abstract protected void handleResponseData(T data);

    abstract protected void handleError(Response<T> response);

    abstract protected void handleException(Exception t);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        this.call = call;
        if (t instanceof Exception) {
            logger.error("ResponseException::{}", t.getMessage());
            handleException((Exception) t);
        }
    }

    private void retryRequest(Call<T> call) {
        if (retryCount++ < TOTAL_RETRIES) {
            logger.info("Request retry!::request::{}", call.request());
            retry();
        }
    }

    private void retry() {
//        call.clone().enqueue(this);
    }
}