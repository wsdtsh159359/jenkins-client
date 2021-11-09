package com.kingdee.java.jenkins.client.vo;


import com.kingdee.java.jenkins.client.constant.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author 高伟
* @date  2020/4/7 14:09
* @descrption 接口返回统一封装对象
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "DataResult")
public class DataResult<T> {

    @ApiModelProperty(value = "状态",position = 1)
    private Integer status;
    @ApiModelProperty(value = "状态简短信息提示",position = 2)
    private String info;
    @ApiModelProperty(value = "消息",position = 3)
    private String message;
    @ApiModelProperty(value = "数据",position = 4)
    private T data;

    public DataResult<T> success() {
        this.status = ErrorCode.SUCCESS_CODE.getCode();
        this.info = ErrorCode.SUCCESS_CODE.getMsg();
        this.message = info;
        return this;
    }


    public DataResult<T> success(T data) {
        this.status = ErrorCode.SUCCESS_CODE.getCode();
        this.info = ErrorCode.SUCCESS_CODE.getMsg();
        this.message = info;
        this.data = data;
        return this;
    }


    public DataResult<T> fail(String message) {
        this.status = ErrorCode.FAIL_CODE.getCode();
        this.info = ErrorCode.FAIL_CODE.getMsg();
        this.message = message;
        return this;
    }

    public DataResult<T> fail(int status, String info, String message) {
        this.status = status;
        this.info = info;
        this.message = message;
        return this;
    }

}
