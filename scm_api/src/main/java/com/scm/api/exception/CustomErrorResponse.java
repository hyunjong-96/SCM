package com.scm.api.exception;

import lombok.Builder;
import lombok.Getter;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * packageName    : com.scm.api.exception
 * fileName       : CustomErrorResponse
 * author         : leehyunjong
 * date           : 2024/11/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/11/17        leehyunjong       최초 생성
 */
@Getter
public class CustomErrorResponse implements Serializable {
    private HttpStatusCode statusCode;
    private int code;
    private String message;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String time;

    @Builder
    public CustomErrorResponse(Exception ex, HttpStatusCode statusCode) {
        this.message = ex.getMessage();
        this.code = statusCode.value();
        this.statusCode = statusCode;
        this.time = LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", this.statusCode);
        jsonObject.put("code", this.code);
        jsonObject.put("message", this.message);
        jsonObject.put("time", this.time);

        return jsonObject.toString();
    }

}
