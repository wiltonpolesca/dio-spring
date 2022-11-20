package dio.spring.diospring.handler;

import java.util.Date;

import lombok.Data;

@Data
public class ResponseError {
    private Date timestamp = new Date();
    private String status = "error";
    private int statusCode = 400;
    private String error;
}
