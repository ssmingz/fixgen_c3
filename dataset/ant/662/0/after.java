class PlaceHold {
  public void setHeader(String src) {
    if (!javadoc1) {
      cmd.createArgument().setValue("-header");
      cmd.createArgument().setValue(src);
    }
  }
}
