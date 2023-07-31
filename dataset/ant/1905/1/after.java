class PlaceHold {
  public void setType(String type) {
    log(
        "DEPRECATED - The setType(String) method has been deprecated."
            + " Use setType(Available.FileDir) instead.",
        MSG_WARN);
    this.type = new FileDir();
    this.type.setValue(type);
  }
}
