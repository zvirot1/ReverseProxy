
package com.example.reverseproxy;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class ProxyControllerX2 {

    private final WebClient webClient;
    private final ThreadPoolExecutor executorService;

    @Value("${proxy.target.url}")
    private String targetUrl;

    public ProxyControllerX2(WebClient.Builder webClientBuilder) {
        // הגדרת ThreadPoolExecutor מותאם אישית
        this.executorService = new ThreadPoolExecutor(
                10, // core pool size
                50, // maximum pool size
                60, TimeUnit.SECONDS, // keep alive time
                new LinkedBlockingQueue<>(1000) // work queue
        );

        LoopResources loopResources = LoopResources.create("http-client", 10, true);

        HttpClient httpClient = HttpClient.create()
                .runOn(loopResources);

        this.webClient = webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @RequestMapping("/**")
    public ResponseEntity<Object> proxy44proxy(
            @RequestBody(required = false) String  requestBody,
            @RequestHeader Map<String, String> headers,
            @RequestParam MultiValueMap<String, String> queryParams,
            HttpMethod method,
            String path) {


        System.out.println("requestBody:" + requestBody);

        JsonObject jsonObject = convertStringToJsonObject(requestBody);

        jsonObject.remove("stream");

        Gson gson = new Gson();
        String modifyRequestBody = gson.toJson(jsonObject);

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.add("X-APG-APIKey", "fsdfdsfsdfsd"); // Add your custom header here

        reqHeaders.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        String url ="http://afsdafsadfdsafdsaf/v1/kyc-to-chatgpt/deployments/gpt4o/chat/completions?api-version=2024-02-01";

        HttpEntity<String> entity = new HttpEntity<>(modifyRequestBody,reqHeaders);
        // ResponseEntity<Object> response = restTemplateWithTrustStore
        ResponseEntity<Object> response = restTemplate
                .exchange(url, method, entity, Object.class);


        System.out.println("\nresponse.getBody()");
        System.out.println(response.getBody());




        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response.getBody(), httpHeaders , HttpStatus.OK);

    }

    @RequestMapping("/p1/**")
    public Mono<ResponseEntity<byte[]>> proxy(
            @RequestBody(required = false) Mono<byte[]> body,
            @RequestHeader Map<String, String> headers,
            @RequestParam MultiValueMap<String, String> queryParams,
            HttpMethod method,
            String path) {

        //String url = targetUrl + path + (queryParams.isEmpty() ? "" : "?" + queryParams.toSingleValueMap().toString().replace("{", "").replace("}", "").replace(", ", "&"));
        String url ="http://agdgfdfgdfd01/v1/kyc-to-chatgpt/deployments/gpt4o/chat/completions?api-version=2024-02-01";


        return body.defaultIfEmpty(new byte[0])
                .flatMap(requestBody -> webClient.method(method)
                        .uri(url)
                        .headers(httpHeaders -> {
                            headers.forEach(httpHeaders::add);
                            httpHeaders.add("X-APG-APIKey", "gfdgdfgdfgd"); // Add your custom header here

                            httpHeaders.remove("Accept");
                            httpHeaders.remove("Authorization");
                            httpHeaders.remove("Postman-Token");
                            httpHeaders.remove("Pragma");
                            httpHeaders.remove("Host");
                            httpHeaders.remove("User-Agent");
                            httpHeaders.remove("Connection");
                            httpHeaders.remove("Cache-Control");


                            httpHeaders.add("Accept", "*/*");
                        })
                        .bodyValue(change(requestBody))
                        .exchangeToMono(response -> response.bodyToMono(DataBuffer.class)
                                .map(dataBuffer -> {
                                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                                    dataBuffer.read(bytes);
                                    DataBufferUtils.release(dataBuffer);
                                    System.out.println("bytes:" + new String(bytes));
                                    try {
                                        System.out.println("bytes Cp1255:" + new String(bytes ,"Cp1255"));
                                        System.out.println("bytes UTF8:" + new String(bytes ,"UTF8"));
                                    } catch (UnsupportedEncodingException e) {
                                      //  throw new RuntimeException(e);
                                    }
                                    //String codePage = detectCodepage(bytes);
                                    //System.out.println("codePage:" + codePage);
                                    return bytes;
                                })
                                .map(bytes -> ResponseEntity.status(response.statusCode())
                                       // .headers(response.headers().asHttpHeaders())

                                        .headers(httpHeaders -> {
                                         //   httpHeaders.add("Content-Type","application/json");
                                            //httpHeaders.add("Content-Type","text/html; charset=utf-8");
                                            httpHeaders.add("Content-Type","application/json; charset=utf-8");
                                            //response.headers().asHttpHeaders();
                                        })

                                        .body(showByte(bytes)))
                        )
                        .onErrorResume(e -> {
                            // טיפול בשגיאות
                            return Mono.just(ResponseEntity.status(500).body(("Error: " + e.getMessage()).getBytes(StandardCharsets.UTF_8)));
                        })
                );
    }

    private byte[] showByte(byte[] bytes){
        try {
            System.out.println("detectCodepage(bytes)");
            System.out.println(detectCodepage(bytes));
            System.out.println("resp bytes " + new String(bytes ));
            System.out.println("resp bytes Cp1255:" + new String(bytes ,"Cp1255"));
            System.out.println("resp bytes UTF8:" + new String(bytes ,"UTF8"));
        } catch (UnsupportedEncodingException e) {
            //  throw new RuntimeException(e);
        }
        return  bytes;

    }

    public static JsonObject convertStringToJsonObject(String jsonString) {
        Gson gson = new Gson();
        System.out.println("jsonString:" + jsonString);
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        return jsonObject;
    }

    private byte[] change(byte[] requestBody) {
        //JsonObject jsonObject = convertStringToJsonObject(new String(requestBody ,StandardCharsets.UTF_8));
        JsonObject jsonObject = convertStringToJsonObject(new String(requestBody));
        jsonObject.remove("stream");
        Gson gson = new Gson();
        return gson.toJson(jsonObject).getBytes();
    }



    public static String detectCodepage(byte[] bytes) {
        CharsetDetector detector = new CharsetDetector();
        detector.setText(bytes);
        CharsetMatch match = detector.detect();

        if (match != null) {
            return match.getName();
        } else {
            return "Unknown";
        }
    }

    // הוספת מתודה לסגירת ה-ExecutorService בצורה נכונה
    public void shutdownExecutorService() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
