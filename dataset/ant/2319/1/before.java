class PlaceHold {
  public synchronized Resource getResource(String name) {
    File f = FILE_UTILS.resolveFile(basedir, name);
    return new Resource(name, f.exists(), f.lastModified(), f.isDirectory(), f.length());
  }
}
