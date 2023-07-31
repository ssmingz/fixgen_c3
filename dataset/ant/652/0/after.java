class PlaceHold {
  public void setWarfile(File warFile) {
    log("DEPRECATED - The warfile attribute is deprecated. Use file attribute instead.");
    super.setFile(warFile);
  }
}
