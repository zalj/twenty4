package com.example.service;

import com.example.model.ComplexHttpRequest;
import com.example.model.ComplexHttpResponse;

import java.util.List;

public interface CustomService {
    ComplexHttpResponse<ComplexHttpRequest> getResultList(ComplexHttpRequest complexHttpRequest);
}
