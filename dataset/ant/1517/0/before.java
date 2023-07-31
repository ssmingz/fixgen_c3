class PlaceHold {
  public Class forceLoadSystemClass(String classname) throws ClassNotFoundException {
    log("force system loading " + classname, MSG_DEBUG);
    Class theClass = findLoadedClass(classname);
    if (theClass == null) {
      theClass = findBaseClass(classname);
    }
    return theClass;
  }
}
