package cn.com.hik.lamp.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/12 14:25
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

    private String code;
    private String message;
    private T data;


}
