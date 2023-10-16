package com.myblog.backend.global.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"httpStatusCode", "httpStatusMessage", "body", "responseDateTime"})
public class BusinessResponseDto<T> implements Serializable {

	private Integer httpStatusCode;
	private String httpStatusMessage;
	private T body;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime responseDateTime;
}
