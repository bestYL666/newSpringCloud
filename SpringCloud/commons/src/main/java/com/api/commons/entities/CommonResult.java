package com.api.commons.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class CommonResult<T>
{
    private Integer code;
    private String message;
    private T data;

//    public CommonResult(Integer code,String message)
//    {
//        this(code,message,null);
//    }

    public CommonResult(Integer code,String message)
    {
        this.code = code;
        this.message = message;
    }
    public CommonResult(Integer code,String message,T data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }



}
