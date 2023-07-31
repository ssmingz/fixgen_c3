class PlaceHold {
  public void execute() throws BuildException {
    int err = -1;
    if ((err = executeJava()) != 0) {
      if (failOnError) {
        throw new BuildException("Java returned: " + err, location);
      } else {
        log("Java Result: " + err, MSG_ERR);
      }
    }
  }
}
