package com.example.backend.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseGen {
    public Boolean success;
    public String message;
    public Object data;

    @Builder.Default
    public Integer pageSize = 0; 

    @Builder.Default
    public Integer pageNumber = 0; 

    @Builder.Default
    public Long totalElements = 0L;

    @Builder.Default
    public Integer totalPages = 0;
    public Boolean isLastPage;
}
