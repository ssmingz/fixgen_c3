class PlaceHold {
  public Resource getResource(String name) {
    return new FileResource(destDir, name);
  }
}
