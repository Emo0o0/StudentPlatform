package bg.tu_varna.sit.api.contract;

public interface OperationProcessor<T extends OperationRequest, P extends OperationResponse> {
    P process(T request);
}
