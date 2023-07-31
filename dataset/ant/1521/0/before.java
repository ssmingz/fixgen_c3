class PlaceHold {
  static BuildException createBuildException(String errMsg, IvjException e) {
    errMsg = (errMsg + "\n") + e.getMessage();
    String[] errors = e.getErrors();
    if (errors != null) {
      for (int i = 0; i < errors.length; i++) {
        errMsg = (errMsg + "\n") + errors[i];
      }
    }
    return new BuildException(errMsg);
  }
}
