class PlaceHold {
  private Class loadClass(String classname) throws Exception {
    if (m_classpath == null) {
      return Class.forName(classname);
    } else {
      final URL[] urls = PathUtil.toURLs(m_classpath);
      final ClassLoader classLoader = new URLClassLoader(urls);
      return classLoader.loadClass(classname);
    }
  }
}
