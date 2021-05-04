package nordside.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import nordside.model.nomenclature.Nomenclature;
import nordside.model.price.PriceTable;
import nordside.service.NomenclatureService;
import nordside.service.PriceTableService;
import nordside.web.json.JacksonObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.List;

import static nordside.utils.ValidationUtil.getStringResponseEntity;


@RestController
@RequestMapping(AdminController.REST_URL)
public class AdminController {
    static final String REST_URL = "rest/admin/";
    //static final String REST_1C_URL = "https://nordside-shop.herokuapp.com/";
    static final String REST_1C_URL = "https://localhost:8080/";

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private NomenclatureService nomenclatureService;

    private PriceTableService priceTableService;

    private JacksonObjectMapper jacksonObjectMapper;

    @Autowired
    public AdminController(NomenclatureService nomenclatureService,PriceTableService priceTableService, JacksonObjectMapper jacksonObjectMapper) {
        this.nomenclatureService = nomenclatureService;
        this.priceTableService = priceTableService;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }




//    @GetMapping(value = "connection")
//    public Mono<Void> checkConnectionWith1C (){
//        {
//
//            Mono<Void> mono = WebClient.builder()
//                    .baseUrl("http://localhost:8080")
//                    .defaultCookie("cookieKey", "cookieValue")
//                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
//                    .build()
//                    .get()
//                    //.uri("*")
//                    .retrieve()
//                    //.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
//                    //        clientResponse -> Mono.empty())
//                    .bodyToMono(Void.class);
//            mono.subscribe(
//                //successValue -> System.out.println(successValue),
//                    successValue -> System.out.println("###success:"+successValue),
//                error -> System.out.println(error.getMessage()),
//                () -> System.out.println("Mono consumed."));
//
//            return mono;
//        }
//    }

    @GetMapping(value = "connection")
    public Boolean checkConnectionWith1C() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = REST_1C_URL.concat("/test/email");
        ResponseEntity<String> response  = restTemplate.getForEntity(resourceUrl, String.class);
        Assert.isTrue(response.getStatusCode().is2xxSuccessful());
        JsonNode jsonNode = jacksonObjectMapper.readTree(response.getBody());


        return response.getStatusCode().is2xxSuccessful();
    }

    @PostMapping(value = "upload/nomenclature", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateNomenclature(@Valid @RequestBody List<Nomenclature> nomenclatureList, BindingResult result){
        if (result.hasErrors()){
            return getStringResponseEntity(result, logger);
        }else{
            int created = nomenclatureService.updateNomenclature(nomenclatureList);
            //logger.info("Created " + created + " nomenclatures");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PostMapping(value = "upload/pricetable",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePriceTable(@Valid @RequestBody List<PriceTable> priceTableList, BindingResult result){
        if (result.hasErrors()){
            return getStringResponseEntity(result,logger);
        }else{
            int created = priceTableService.updatePriceTable(priceTableList);
            //logger.info("Created " + created + " price lines");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

}
/*





//RestTemplate

//create
RestTemplate restTemplate = new RestTemplate();
String fooResourceUrl  = "http://localhost:8080/spring-rest/foos";
ResponseEntity<String> response  = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));


//WebClient

//
UriSpec<RequestBodySpec> uriSpec = client.post();//методы GET,POST,DELETE
//
RequestBodySpec bodySpec = uriSpec.uri("/resource");//URL
//body
RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue("data");
or
RequestHeadersSpec<?> headersSpec = bodySpec.body(Mono.just(new Foo("name")), Foo.class);
or
RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue("data"));
or
RequestHeadersSpec headersSpec = bodySpec.body(BodyInserters.fromPublisher(Mono.just("data")),String.class);
or
LinkedMultiValueMap map = new LinkedMultiValueMap();
map.add("key1", "value1");
map.add("key2", "value2");
RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromMultipartData(map));

//
ResponseSpec responseSpec = headersSpec.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
  .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
  .acceptCharset(StandardCharsets.UTF_8)
  .ifNoneMatch("*")
  .ifModifiedSince(ZonedDateTime.now())
  .retrieve();

//
Mono<String> response = headersSpec.exchangeToMono(response -> {
  if (response.statusCode().equals(HttpStatus.OK)) {
      return response.bodyToMono(String.class);
  } else if (response.statusCode().is4xxClientError()) {
      return Mono.just("Error response");
  } else {
      return response.createException()
        .flatMap(Mono::error);
  }
});

or

Mono<String> response = headersSpec.retrieve().bodyToMono(String.class);

//create
WebClient webClient = WebClient.create();
or
WebClient webClient = WebClient.create("https://client-domain.com");
or
WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:3000")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

//request
Employee createdEmployee = webClient
        .post()
        .uri("/employees")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Mono.just(empl), Employee.class)
        .retrieve()
        .bodyToMono(Employee.class);



 */