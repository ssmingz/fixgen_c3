class PlaceHold {
  public void setDoctitle(String src) {
    if (!javadoc1) {
      cmd.createArgument().setValue("-doctitle");
      cmd.createArgument().setValue(src);
    }
  }
}
