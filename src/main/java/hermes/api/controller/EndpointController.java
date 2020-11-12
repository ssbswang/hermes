package hermes.api.controller;

import hermes.model.Model;
import hermes.model.RequestDTO;
import hermes.jms.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/endpoint")
@RestController
@Api(tags = "spring-artemis", description = "Spring-Artemis")
public class EndpointController {

  private static final Logger log = LoggerFactory.getLogger(EndpointController.class);

  @Autowired
  Producer producer;

  @ApiOperation(value = "Send a request via Artemis")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Request successful"),
      @ApiResponse(code = 400, message = "Bad Request"),
  })
  @PostMapping
  public void produce(
      @ApiParam(value = "param1", required = true)
      @RequestParam(name = "param1") String issue,
      @ApiParam(value = "param2", required = true)
      @RequestParam(name = "param2") Model model) {
    producer.send(new RequestDTO(issue, model.getVal()));
  }

}
