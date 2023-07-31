class PlaceHold {
  public AntClassLoader createClassLoader(Path path) {
    return AntClassLoader.newAntClassLoader(getClass().getClassLoader(), this, path, true);
  }
}
