class PlaceHold {
  private Class loadClass(String classname) throws Exception {
    if (classpath == null) {
      return Class.forName(classname);
    } else {
      AntClassLoader al = getProject().createClassLoader(classpath);
      Class c = al.loadClass(classname);
      AntClassLoader.initializeClass(c);
      return c;
    }
  }
}
