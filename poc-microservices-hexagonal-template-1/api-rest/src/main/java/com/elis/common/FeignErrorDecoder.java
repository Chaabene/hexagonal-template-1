package com.elis.common;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {


    public static final String ERROR_TO_SEND_HTTP_REQUEST_STATUS_CODE_S_METHOD_KEY_S = "Error took place when using Feign client to send HTTP Request. Status code  %s , methodKey =  %s";

    @Override
    public Exception decode(String methodKey, Response response) {
        String errorMessage = String.format(ERROR_TO_SEND_HTTP_REQUEST_STATUS_CODE_S_METHOD_KEY_S, response.status(), methodKey);
        log.error(errorMessage);
        return new ResponseStatusException(HttpStatus.valueOf(response.status()), errorMessage);
    }

}
