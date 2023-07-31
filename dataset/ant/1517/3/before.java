class PlaceHold {
  public Class forceLoadClass(String classname) throws ClassNotFoundException {
    log("force loading " + classname, MSG_DEBUG);
    Class theClass = findLoadedClass(classname);
    if (theClass == null) {
      theClass = findClass(classname);
    }
    return theClass;
  }
}
