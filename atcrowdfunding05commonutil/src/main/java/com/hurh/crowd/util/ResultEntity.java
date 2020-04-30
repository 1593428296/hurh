package com.hurh.crowd.util;

/**
 * @PackAgeName:com.hurh.crowd.util
 * @ClassName:ResultEntity
 * @Description: 统一整个项目中Ajax请求返回的结果(未来也可以用于分布式架构各个模块间调用时返回统一类型)
 * @Author:hrh
 * @Date:2020/4/24
 */
public class ResultEntity<T> {

    public static  final  String SUCCESS = "SUCCESS";
    public static  final  String FAILED = "FAILED";

    //封装当前请求处理的结果是成功还是失败
    private String result;

    //请求处理失败时返回的错误信息
    private String message;

    //要返回的数据
    private T data;

    /*
     * @Author: hrh
     * @Description: 请求处理成功且不需要返回数据时使用的工具方法
     * @Date: 2020/4/24 22:45
     * @Param: []
     * @return com.hurh.crowd.util.ResultEntity<Type>
     **/
    public static <Type> ResultEntity<Type> successWithoutData() {
        return new ResultEntity<Type>(SUCCESS, null, null);
    }
    /*
     * @Author: hrh
     * @Description: 请求处理成功且需要返回数据时使用的工具方法
     * @Date: 2020/4/24 22:45
     * @Param: [data] 要返回的数据
     * @return com.hurh.crowd.util.ResultEntity<Type>
     **/
    public static <Type> ResultEntity<Type> successWithData(Type data) {
        return new ResultEntity<Type>(SUCCESS, null, data);
    }
    /*
     * @Author: hrh
     * @Description: 请求处理失败后使用的工具方法
     * @Date: 2020/4/24 22:49
     * @Param: [message] 失败的错误消息
     * @return com.hurh.crowd.util.ResultEntity<Type>
     **/
    public static <Type> ResultEntity<Type> failed(String message) {
        return new ResultEntity<Type>(FAILED, message, null);
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public ResultEntity(String result, String message, T data) {
        super();
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ResultEntity() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
