class PlaceHold {
  public Class findClass(String name) throws ClassNotFoundException {
    log("Finding class " + name, MSG_DEBUG);
    return findClassInComponents(name);
  }
}
