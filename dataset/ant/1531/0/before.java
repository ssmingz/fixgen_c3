class PlaceHold {
  public void execute() throws BuildException {
    File savedDir = dir;
    int err = -1;
    try {
      if ((err = executeJava()) != 0) {
        if (failOnError) {
          throw new BuildException("Java returned: " + err, location);
        } else {
          log("Java Result: " + err, MSG_ERR);
        }
      }
    } finally {
      dir = savedDir;
    }
  }
}
