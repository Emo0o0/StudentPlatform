package com.example.api.contract;

public interface OperationProcessor<T extends OperationRequest, P extends OperationResponse> {
    P process(T request);
}
