package com.example.service.impl;

import com.example.model.ComplexHttpRequest;
import com.example.model.ComplexHttpResponse;
import com.example.service.CustomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Lazy
@Service
public class CustomServiceImpl implements CustomService {
    @Override
    public ComplexHttpResponse<ComplexHttpRequest> getResultList(ComplexHttpRequest complexHttpRequest) {
        handle(complexHttpRequest);
        return ComplexHttpResponse.of(complexHttpRequest, complexHttpRequest.hashCode());
    }

    private void handle(ComplexHttpRequest request) {
        int sum = request.getList().stream().reduce(Integer::sum).get();
        request.getList().add(sum);
        request.setName(StringUtils.reverse(request.getName()));
    }
}
