class PlaceHold {
  public void setJarfile(File jarFile) {
    log("DEPRECATED - The jarfile attribute is deprecated. Use file attribute instead.");
    super.setFile(jarFile);
  }
}
