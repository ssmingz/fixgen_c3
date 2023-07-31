class PlaceHold {
  public void execute() throws BuildException {
    File savedDir = dir;
    checkConfiguration();
    if (isValidOs()) {
      try {
        runExec(prepareExec());
      } finally {
        dir = savedDir;
      }
    }
  }
}
