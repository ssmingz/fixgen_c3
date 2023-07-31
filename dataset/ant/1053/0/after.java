class PlaceHold {
  public void execute() throws BuildException {
    File savedDir = dir;
    cmdl.setExecutable(resolveExecutable());
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
