package com.pada.sandbox.grpc;


import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import pada.testowe.GreetingServiceGrpc;
import pada.testowe.ParkRequest;
import pada.testowe.ParkResponse;

@GrpcService
public class CarParkServiceImpl  extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void sayHello(ParkRequest request, StreamObserver<ParkResponse> responseObserver) {
        System.out.println("Hi from gRPC");
        ParkResponse response = ParkResponse.newBuilder().setResponseMessage("Hello gRPC").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

