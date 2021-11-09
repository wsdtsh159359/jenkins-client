package com.kingdee.java.jenkins.client.config;


import com.kingdee.java.jenkins.client.vo.DataResult;
import com.kingdee.java.jenkins.client.constant.ErrorCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
* @author 高伟
* @date  2020/4/7 14:05
* @descrption 全局异常处理
*/
@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private LocaleUtil localeUtil;
    /**
     * 运行时异常
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public DataResult<String> runtimeExceptionHandler(RuntimeException ex) {
        return result(ErrorCode.RUNTIME_EXCEPTION.getCode(), ex);
    }

    /**
     * IO异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public DataResult<String> ioExceptionHandler(IOException ex) {
        return result(ErrorCode.IO_EXCEPTION.getCode(), ex);
    }




    /**
     * 结果集
     *
     * @param errCode
     * @param e
     * @return
     */
//    private DataResult<String> result(int errCode, BizRuntimeException e) {
//        log.error(e.getMessage(), e);
//        String localValue = localeUtil.getMessage(String.valueOf(errCode));
//        if (!StringUtils.isEmpty(localValue)) {
//            return new DataResult<String>().fail(errCode, localValue, e.getMessage());
//        } else {
//            return new DataResult<String>().fail(errCode, e.getError(), e.getMessage());
//        }
//    }

    private DataResult<String> result(int errCode, Exception e) {
        return new DataResult<String>().fail(errCode, localeUtil.getMessage(String.valueOf(errCode)), e.getMessage());
    }


}
