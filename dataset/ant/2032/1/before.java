class PlaceHold {
  public AntClassLoader createClassLoader(ClassLoader parent, Path path) {
    return new AntClassLoader(parent, this, path);
  }
}
