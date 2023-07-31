class PlaceHold {
  public synchronized Resource getResource(String name) {
    return new FileResource(basedir, name);
  }
}
