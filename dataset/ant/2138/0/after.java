class PlaceHold {
  public void setDirectory(boolean directory) {
    checkAttributesAllowed();
    this.directory = (directory) ? Boolean.TRUE : Boolean.FALSE;
  }
}
