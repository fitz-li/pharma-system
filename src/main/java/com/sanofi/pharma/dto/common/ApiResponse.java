package com.sanofi.pharma.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T> {

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(T data, Long total) {
        this.data = data;
        this.pagination = new Pagination(total);
    }

    /**
     * Create an ApiResponse with a list of data and pagination info from a Page.
     *
     * @param pageData the Spring Data Page
     * @param <U>      the element type
     * @return ApiResponse containing the list of U and pagination
     */
    public static <U> ApiResponse<List<U>> fromPage(Page<U> pageData) {
        return new ApiResponse<>(pageData.getContent(), pageData.getTotalElements());
    }

    private T data;
    private Pagination pagination;

    private ErrorInfo error;
}
