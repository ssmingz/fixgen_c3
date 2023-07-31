class PlaceHold {
  public Class forceLoadSystemClass(String classname) throws ClassNotFoundException {
    project.log("force system loading " + classname, MSG_DEBUG);
    Class theClass = findLoadedClass(classname);
    if (theClass == null) {
      theClass = findSystemClass(classname);
    }
    return theClass;
  }
}
