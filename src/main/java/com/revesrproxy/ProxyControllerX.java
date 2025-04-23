package com.example.reverseproxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.core.io.buffer.DataBufferUtils;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.core.io.buffer.DataBuffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@RestController
public class ProxyControllerX {

    private final WebClient webClient;

    @Value("${proxy.target.url}")
    private String targetUrl;

    public ProxyControllerX(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @GetMapping("/v1/models")
    public String getModels() {
        return "{\n" +
                "    \"object\": \"list\",\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": \"o3-mini-2025-01-31\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1738010200,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-realtime-preview-2024-12-17\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1733945430,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"o3-mini\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1737146383,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-audio-preview-2024-12-17\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1734034239,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"dall-e-3\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1698785189,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"dall-e-2\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1698798177,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-audio-preview-2024-10-01\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1727389042,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-realtime-preview-2024-10-01\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1727131766,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-transcribe\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1742068463,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini-transcribe\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1742068596,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-realtime-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1727659998,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"babbage-002\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1692634615,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini-tts\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1742403959,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"tts-1-hd-1106\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1699053533,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"text-embedding-3-large\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1705953180,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1687882411,\n" +
                "            \"owned_by\": \"openai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"text-embedding-ada-002\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1671217299,\n" +
                "            \"owned_by\": \"openai-internal\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"omni-moderation-latest\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1731689265,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"computer-use-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1734655677,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"tts-1-hd\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1699046015,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"computer-use-preview-2025-03-11\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1741377021,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini-audio-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1734387424,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-audio-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1727460443,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"o1-preview-2024-09-12\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1725648865,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini-realtime-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1734387380,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini-realtime-preview-2024-12-17\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1734112601,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-3.5-turbo-instruct-0914\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1694122472,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini-search-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1741391161,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"tts-1-1106\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1699053241,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"davinci-002\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1692634301,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-3.5-turbo-1106\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1698959748,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4-turbo\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1712361441,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4-0125-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1706037612,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-3.5-turbo-instruct\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1692901427,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-3.5-turbo\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1677610602,\n" +
                "            \"owned_by\": \"openai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4-turbo-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1706037777,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"chatgpt-4o-latest\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1723515131,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini-search-preview-2025-03-11\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1741390858,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-2024-11-20\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1739331543,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"whisper-1\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1677532384,\n" +
                "            \"owned_by\": \"openai-internal\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-3.5-turbo-0125\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1706048358,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-2024-05-13\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1715368132,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-3.5-turbo-16k\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1683758102,\n" +
                "            \"owned_by\": \"openai-internal\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4-turbo-2024-04-09\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1712601677,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4-1106-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1698957206,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"o1-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1725648897,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4-0613\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1686588896,\n" +
                "            \"owned_by\": \"openai\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-search-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1741388720,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4.5-preview\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1740623059,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4.5-preview-2025-02-27\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1740623304,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-search-preview-2025-03-11\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1741388170,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"tts-1\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1681940951,\n" +
                "            \"owned_by\": \"openai-internal\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"omni-moderation-2024-09-26\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1732734466,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"text-embedding-3-small\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1705948997,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1715367049,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1721172741,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-2024-08-06\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1722814719,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini-2024-07-18\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1721172717,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"o1-mini\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1725649008,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"gpt-4o-mini-audio-preview-2024-12-17\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1734115920,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"o1-2024-12-17\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1734326976,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"o1-mini-2024-09-12\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1725648979,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"o1\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1734375816,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"o1-pro\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1742251791,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": \"o1-pro-2025-03-19\",\n" +
                "            \"object\": \"model\",\n" +
                "            \"created\": 1742251504,\n" +
                "            \"owned_by\": \"system\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }



/*
    public Mono<String> getRequestBodyAsString(ServerWebExchange exchange) {
        return exchange.getRequest().getBody()
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    return new String(bytes);
                })
                .reduce((s1, s2) -> s1 + s2);
    }

    public Mono<Void> handleRequest(ServerWebExchange exchange) {
        return getRequestBodyAsString(exchange)
                .flatMap(body -> {
                    // כאן אתה יכול לעבוד עם המחרוזת body בצורה ריאקטיבית
                    System.out.println(body);
                    // החזרת Mono<Void> כדי להמשיך את הזרם הריאקטיבי
                    return Mono.empty();
                });
    }
    */

    @RequestMapping("/ccc/**")
    public Mono<ResponseEntity<byte[]>> proxy(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        System.out.println("path: " + path );
        String query = exchange.getRequest().getURI().getQuery();
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        HttpMethod method = exchange.getRequest().getMethod();
        Flux<DataBuffer> body = exchange.getRequest().getBody();

        //      byte[] bytes = new byte[dataBuffer.readableByteCount()];
//        dataBuffer.read(bytes);



        Mono<String> bodyStringMono = DataBufferUtils.join(body)
                .map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    System.out.println(new String(bytes, StandardCharsets.UTF_8));
                    return new String(bytes, StandardCharsets.UTF_8);
                });





        System.out.println("Incoming request: method=" + exchange.getRequest().getMethod() +
                "\n path=" + path +
                "\n query=" + query +
                "\n queryParams=" + queryParams +
                "\n method=" + method +
                "\n headers=" + exchange.getRequest().getHeaders()   );

        System.out.println("-------------");
        // handleRequest(exchange);
        System.out.println("-------------");

        //String url = targetUrl + path + (query != null ? "?" + query : "");
        String url ="xxxxxxxxxxxxxxxxxxxx";


        return webClient.method(exchange.getRequest().getMethod())
                .uri(url)
                .headers(headers -> {
                    headers.addAll(exchange.getRequest().getHeaders());
                    headers.add("X-APG-APIKey", "xxxxxxxxxxxxxxxxxxxxxxxxxxxx"); // הוסף את הכותרת שלך כאן

                    headers.remove("Accept");
                    headers.remove("Authorization");
                    headers.remove("Postman-Token");
                    headers.remove("Pragma");
                    headers.remove("Host");
                    headers.remove("User-Agent");

                    headers.add("Accept" ,"*/*");

                })
                .body(exchange.getRequest().getBody(), DataBuffer.class)
                .exchangeToMono(response -> response.bodyToMono(DataBuffer.class)
                        .map(dataBuffer -> {
                            ByteBuffer byteBuffer = dataBuffer.asByteBuffer();
                            byte[] bytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bytes);
                            System.out.println(new String(bytes));
                            return bytes;
                        })
                        .map(bytes -> ResponseEntity.status(response.statusCode())
                                .headers(response.headers().asHttpHeaders())
                                .body(bytes)));
    }
}
