class PlaceHold {
  public void execute() throws TaskException {
    try {
      validate();
      doMail();
    } catch (Exception e) {
      if (failOnError) {
        throw new TaskException("Error", e);
      } else {
        String text = e.toString();
        getContext().error(text);
      }
    }
  }
}
