package com.pms.utils;

import com.pms.beans.EmpAndUserResponse;
import com.pms.beans.FileAndObjectTypeBean;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class EmployeeOperations {

    private ResponseEntity responseEntity;

    @Value("${employee.fetch.by.email.API}")
    private String fetchEmpByEmail;

    @Value("${employee.fetch.by.empId.API}")
    private String fetchEmpByEmpId;

    @Value("${employee.fetch.get.all.API}")
    private String fetchAllEmp;
    @Autowired
    WebClient webClient;

    @CircuitBreaker(name = "empService", fallbackMethod = "fallbackEmployeeData")
    @TimeLimiter(name = "empService", fallbackMethod = "fallbackEmployeeData")
    public CompletableFuture<EmpAndUserResponse> getEmpByEmailId(String emailId){
        return webClient.get()
                .uri(fetchEmpByEmail+emailId)
                .retrieve()
                .bodyToMono(EmpAndUserResponse.class)
                .timeout(Duration.ofSeconds(120)).toFuture();
    }

    @CircuitBreaker(name = "empService", fallbackMethod = "fallbackEmployeeData")
    @TimeLimiter(name = "empService", fallbackMethod = "fallbackEmployeeData")
    public CompletableFuture<EmpAndUserResponse> getEmpByEmpId(long empId){
        return webClient.get()
                .uri(fetchEmpByEmpId+empId)
                .retrieve()
                .bodyToMono(EmpAndUserResponse.class)
                .timeout(Duration.ofSeconds(120)).toFuture();
    }


    @CircuitBreaker(name = "empService", fallbackMethod = "fallbackAllEmployee")
    @TimeLimiter(name = "empService", fallbackMethod = "fallbackAllEmployee")
    public CompletableFuture<List<FileAndObjectTypeBean>> getAllEmp() {
        return webClient.get()
                .uri(fetchAllEmp)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<FileAndObjectTypeBean>>() {})
                .timeout(Duration.ofSeconds(60)).toFuture();
    }

    public CompletableFuture<EmpAndUserResponse> fallbackEmployeeData(Throwable t) {
        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<FileAndObjectTypeBean> fallbackAllEmployee(Throwable t) {
        return CompletableFuture.completedFuture(null);
    }


}
