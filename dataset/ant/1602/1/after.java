class PlaceHold {
  public void setExtdirs(String src) {
    if (!javadoc1) {
      cmd.createArgument().setValue("-extdirs");
      cmd.createArgument().setValue(src);
    }
  }
}
