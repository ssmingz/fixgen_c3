class PlaceHold {
  public void execute() throws BuildException {
    try {
      validate();
      doMail();
    } catch (Exception e) {
      if (failOnError) {
        throw new BuildException("Error", e);
      } else {
        String text = e.toString();
        log(text, MSG_ERR);
      }
    }
  }
}
