package cn.com.hik.lamp.common.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hcb
 * @description :
 * @date : 2020/5/12 14:25
 */
//@Builder
@Data
@NoArgsConstructor
public class RequestData<T> {

    private T request;
}
