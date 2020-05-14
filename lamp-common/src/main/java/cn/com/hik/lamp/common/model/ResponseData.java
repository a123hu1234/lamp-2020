package cn.com.hik.lamp.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/12 14:25
 */
@Builder
@Data

public class ResponseData<T> {


//    public ResponseData(){
//
//    }

    public ResponseData(String code,String message,T data){
        this.code = code;
       this.message = message;
        this.data = data;
    }

    private String code;
    private String message;
    private T data;


}
