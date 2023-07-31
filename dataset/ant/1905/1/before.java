class PlaceHold {
  public void setType(String type) {
    log(
        "DEPRECATED - The setType(String) method has been deprecated."
            + " Use setType(Available.FileDir) instead.");
    this.type = new FileDir();
    this.type.setValue(type);
  }
}
