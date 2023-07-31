class PlaceHold {
  private Class loadClass(String classname) throws Exception {
    if (m_classpath == null) {
      return Class.forName(classname);
    } else {
      AntClassLoader al = new AntClassLoader(getProject(), m_classpath);
      Class c = al.loadClass(classname);
      AntClassLoader.initializeClass(c);
      return c;
    }
  }
}
