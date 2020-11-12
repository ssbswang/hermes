package hermes.model;

/**
 * Communication/transfer object for producer-consumer as well as consumer-to-api
 */
public class RequestDTO {

  private String param1;
  private String param2;

  // empty constructor used for Beans for message conversion/serialization
  public RequestDTO() {}

  public RequestDTO(String param1, String param2) {
    this.param1 = param2;
    this.param1 = param1;
  }

  public String getIssue() {
    return this.param1;
  }

  public String getResult() {
    return this.param2;
  }

}
