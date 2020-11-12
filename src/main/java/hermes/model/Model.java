package hermes.model;

public enum Model {
  STATE1("val1"),
  STATE2("val2");

  private String val;

  Model(String val) {
    this.val = val;
  }

  public String getVal() {
    return this.val;
  }
}
