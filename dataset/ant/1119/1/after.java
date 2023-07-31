class PlaceHold {
  public Class findClass(String name) throws ClassNotFoundException {
    project.log("Finding class " + name, MSG_DEBUG);
    try {
      return findClass(name, classpath);
    } catch (ClassNotFoundException e) {
      throw e;
    }
  }
}
