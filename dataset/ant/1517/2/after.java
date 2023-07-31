class PlaceHold {
  public Class forceLoadSystemClass(String classname) throws ClassNotFoundException {
    getLogger().debug("force system loading " + classname);
    Class theClass = findLoadedClass(classname);
    if (theClass == null) {
      theClass = findBaseClass(classname);
    }
    return theClass;
  }
}
