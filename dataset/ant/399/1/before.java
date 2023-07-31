class PlaceHold {
  private Class loadClass(String classname) throws Exception {
    if (m_classpath == null) {
      return Class.forName(classname);
    } else {
      final ClassLoader classLoader = new URLClassLoader(m_classpath.toURLs());
      return classLoader.loadClass(classname);
    }
  }
}
