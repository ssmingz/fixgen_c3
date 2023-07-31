class PlaceHold {
  public Resource getResource(String name) {
    File src = FILE_UTILS.resolveFile(destDir, name);
    return new Resource(name, src.exists(), src.lastModified(), src.isDirectory());
  }
}
