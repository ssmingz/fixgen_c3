class InputRequest {
  public InputRequest(String prompt) {
    if (prompt == null) {
      throw new IllegalArgumentException("prompt must not be null");
    }
    this.prompt = prompt;
  }
}
