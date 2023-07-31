class PlaceHold {
  public Resource getResource(String name) {
    File src = fileUtils.resolveFile(destDir, name);
    return new Resource(name, src.exists(), src.lastModified(), src.isDirectory());
  }
}
