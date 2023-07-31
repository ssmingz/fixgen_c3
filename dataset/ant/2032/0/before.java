class PlaceHold {
  public AntClassLoader createClassLoader(Path path) {
    return new AntClassLoader(getClass().getClassLoader(), this, path);
  }
}
