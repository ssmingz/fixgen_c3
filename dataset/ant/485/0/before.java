class PlaceHold {
  public void execute() throws BuildException {
    File savedDir = dir;
    int err = -1;
    try {
      err = executeJava();
      if (fork && Execute.isFailure(err)) {
        if (failOnError) {
          throw new BuildException("Java returned: " + err, getLocation());
        } else {
          log("Java Result: " + err, MSG_ERR);
        }
      }
      maybeSetResultPropertyValue(err);
    } finally {
      dir = savedDir;
    }
  }
}
