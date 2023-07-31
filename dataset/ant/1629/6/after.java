class PlaceHold {
  private Class loadClass(String classname) throws Exception {
    if (classpath == null) {
      return Class.forName(classname);
    } else {
      AntClassLoader al = getProject().createClassLoader(classpath);
      Class c = Class.forName(classname, true, al);
      return c;
    }
  }
}
