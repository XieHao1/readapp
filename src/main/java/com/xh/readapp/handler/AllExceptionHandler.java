package com.xh.readapp.handler;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.xh.readapp.dictionary.ErrorEnum;
import com.xh.readapp.util.ResultJson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class AllExceptionHandler {

    @ExceptionHandler
    private ResultJson systemException(Exception ex){
        ex.printStackTrace();
        return ResultJson.fail(ErrorEnum.SYSTEM_ERROR.getCode(), ErrorEnum.SYSTEM_ERROR.getMsg());
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResultJson signatureVerificationException(Exception ex){
        ex.printStackTrace();
        return ResultJson.fail(ErrorEnum.TOKEN_SIGN_ERROR.getCode(),ErrorEnum.TOKEN_SIGN_ERROR.getMsg());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResultJson tokenExpiredException(Exception ex){
        ex.printStackTrace();
        return ResultJson.fail(ErrorEnum.TOKEN_EXPIRED_ERROR.getCode(),ErrorEnum.TOKEN_EXPIRED_ERROR.getMsg());
    }

    @ExceptionHandler(AlgorithmMismatchException.class)
    public ResultJson algorithmMismatchException(Exception ex){
        ex.printStackTrace();
        return ResultJson.fail(ErrorEnum.TOKEN_ALGORITHM_ERROR.getCode(),ErrorEnum.TOKEN_ALGORITHM_ERROR.getMsg());
    }

    @ExceptionHandler(InvalidClaimException.class)
    public ResultJson invalidClaimException(Exception ex){
        ex.printStackTrace();
        return ResultJson.fail(ErrorEnum.TOKEN_INVALID_ERROR.getCode(), ErrorEnum.TOKEN_INVALID_ERROR.getMsg());
    }

}
