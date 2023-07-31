class PlaceHold {
  public void setReverseLoader(boolean reverseLoader) {
    this.cpDelegate.setReverseLoader(reverseLoader);
    log("The reverseloader attribute is DEPRECATED. It will be removed", MSG_WARN);
  }
}
