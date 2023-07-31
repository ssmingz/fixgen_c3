class PlaceHold {
  public Class findClass(String name) throws ClassNotFoundException {
    getLogger().debug("Finding class " + name);
    return findClassInComponents(name);
  }
}
