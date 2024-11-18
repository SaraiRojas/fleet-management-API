package com.course.fleet.management.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomErrorResponseDTO {

  private HttpStatus statusCode;

  private String errorMessage;

  private String field;

  private String value;

  private String path;
}
