class PlaceHold {
  public MultipleChoiceInputRequest(String prompt, Vector choices) {
    super(prompt);
    if (choices == null) {
      throw new IllegalArgumentException("choices must not be null");
    }
    this.choices = choices;
  }
}
