class PlaceHold {
  public void setCharset(String src) {
    if (!javadoc1) {
      cmd.createArgument().setValue("-charset");
      cmd.createArgument().setValue(src);
    }
  }
}
