package com.example.backend.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    public Object data;
    public Integer pageSize; 
    public Integer pageNumber; 
    public Long totalElements;
    public Integer totalPages;
    public Boolean isLastPage;
}
