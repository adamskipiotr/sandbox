package com.pada.sandbox.grpc;


import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import pada.testowe.*;

@GrpcService
public class CarParkServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void sayHello(ParkRequest request, StreamObserver<ParkResponse> responseObserver) {
        System.out.println("Hi from gRPC");
        ParkResponse response = ParkResponse.newBuilder().setResponseMessage("Hello gRPC").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void streamUsers(Empty request, StreamObserver<ParkResponseManyTimes> responseObserver) { // Streamowanie od serwera do klienta
        for (int i = 1; i < 6; i++) {
            ParkResponseManyTimes parkResponseManyTimes = ParkResponseManyTimes.newBuilder()
                    .setCount(i)
                    .setMessage(String.format("Hello %d car", i))
                    .build();
            responseObserver.onNext(parkResponseManyTimes);
        }
        responseObserver.onCompleted();
    }


    @Override
    public StreamObserver<ParkRequest> uploadUsers(StreamObserver<ParkResponse> responseObserver) {

        return new StreamObserver<>() {
            private final StringBuilder builder = new StringBuilder();

            @Override
            public void onNext(ParkRequest request) {
                // Append each incoming vehicle info
                Vehicle vehicle = request.getVehicle();
                builder.append("Received vehicle: ")
                        .append(vehicle.getVehicleNumber())
                        .append(" (").append(vehicle.getVehicleType()).append(")\n");
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error receiving stream from client: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // When client is done streaming, respond once
                String summary = builder.toString();
                System.out.println(summary);
                ParkResponse response = ParkResponse.newBuilder()
                        .setResponseMessage("Server received:\n" + summary)
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<ParkRequest> chat(StreamObserver<ParkResponseManyTimes> responseObserver) { // Dwukierunkowe streamowanie
        return super.chat(responseObserver);
    }
}

