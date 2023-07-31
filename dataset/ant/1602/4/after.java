class PlaceHold {
  public void setLocale(String src) {
    if (!javadoc1) {
      cmd.createArgument().setValue("-locale");
      cmd.createArgument().setValue(src);
    }
  }
}
