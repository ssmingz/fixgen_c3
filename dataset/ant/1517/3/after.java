class PlaceHold {
  public Class forceLoadClass(String classname) throws ClassNotFoundException {
    getLogger().debug("force loading " + classname);
    Class theClass = findLoadedClass(classname);
    if (theClass == null) {
      theClass = findClass(classname);
    }
    return theClass;
  }
}
