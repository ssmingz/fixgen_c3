class PlaceHold {
  public Class forceLoadClass(String classname) throws ClassNotFoundException {
    Class theClass = findLoadedClass(classname);
    if (theClass == null) {
      theClass = findClass(classname);
    }
    return theClass;
  }
}
