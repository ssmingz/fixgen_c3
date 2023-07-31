class PlaceHold {
  public void setFooter(String src) {
    if (!javadoc1) {
      cmd.createArgument().setValue("-footer");
      cmd.createArgument().setValue(src);
    }
  }
}
