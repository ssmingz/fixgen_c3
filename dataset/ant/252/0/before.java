class PlaceHold {
  protected String getVerboseParameter() {
    return _verbose ? null : "/quiet";
  }
}
