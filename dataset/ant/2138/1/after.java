class PlaceHold {
  public void setExists(boolean exists) {
    checkAttributesAllowed();
    this.exists = (exists) ? Boolean.TRUE : Boolean.FALSE;
  }
}
