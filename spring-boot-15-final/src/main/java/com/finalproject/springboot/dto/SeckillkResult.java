package com.finalproject.springboot.dto;

/**
 * @program: mengxuegu
 * @description: 枚举类型，反射执行过程的状态，直接封装json返回的结果结构与形式
 *                          这里要记得，dto设计模式，tingshuo已经过时。再次强调
 * @author: Lunatic Princess
 * @create: 2019-05-04 22:09
 **/
public class SeckillkResult<T> {             /** 写少报错 */

    /** zhuang态标记位 */
    private boolean success;

    private T data;

    /** 错误说明 */
    private String error;

    public SeckillkResult(boolean success, T data) {          /** 两个构造器，第一个用来封装状态标记数据
                                                                    第二个用来封装错误的状态标记数据 */
        this.success = success;
        this.data = data;
    }

    public SeckillkResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SeckillkResult{" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
