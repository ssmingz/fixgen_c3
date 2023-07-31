class PlaceHold {
  protected String getVerboseParameter() {
    return verbose ? null : "/quiet";
  }
}
