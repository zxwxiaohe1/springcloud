package com.self.cloud.common.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Function;

public class ReturnResult<T> implements Serializable
{
       /**
        * 状态码
        */
       private Integer status;
       /**
        * 信息
        */
       private String message;
       /**
        * 数据体
        */
       private T data;

       public Integer getStatus() {
              return status != null ? status : 0;
       }

       public void setStatus(int status) {
              this.status = status;
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

       public ReturnResult()
       {
              status = 200;
              message="sucess";
       }

       public ReturnResult(int status, String message, T data)
       {
              this.status = status;
              this.message = message;
              this.data = data;
       }

       public <U> ReturnResult<U> withData(U data) {
              return new ReturnResult<>(status, message, data);
       }

       public <U> ReturnResult<U> map(Function<T, U> mapFunc) {
              return data != null ? this.withData(mapFunc.apply(data)) : this.<U>withData(null);
       }

       @Override
       public boolean equals(Object o)
       {
              if (this == o)
                     return true;
              if (o == null || getClass() != o.getClass())
                     return false;
              ReturnResult<?> that = (ReturnResult<?>) o;
              return Objects.equals(status, that.status) && Objects.equals(message, that.message) && Objects.equals(data, that.data);
       }

       @Override
       public int hashCode()
       {
              return Objects.hash(status, message, data);
       }

       @Override
       public String toString()
       {
              return new StringJoiner(", ", ReturnResult.class.getSimpleName() + "[", "]")
                      .add("status=" + status)
                      .add("message='" + message + "'")
                      .add("data=" + data)
                      .toString();
       }
}
