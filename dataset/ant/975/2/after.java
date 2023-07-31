class PlaceHold {
  public void stdout(final String line) {
    if (util.match("/^exit/", line)) {
      return;
    }
    if (util.match("/error:/", line) && (!util.match("/up-to-date/", line))) {
      registerError(new TaskException(line));
    }
    getContext().info(util.substitute("s/^.*: //", line));
  }
}
