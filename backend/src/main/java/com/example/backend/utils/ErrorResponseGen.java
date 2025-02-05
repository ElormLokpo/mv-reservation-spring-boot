package com.example.backend.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseGen {
    public Integer statusCode;
    public String message;

    @Builder.Default
    public String details = "";
}
