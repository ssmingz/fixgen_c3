class PlaceHold {
  public AntClassLoader createClassLoader(ClassLoader parent, Path path) {
    return AntClassLoader.newAntClassLoader(parent, this, path, true);
  }
}
