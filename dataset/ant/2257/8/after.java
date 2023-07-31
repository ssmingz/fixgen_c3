class PlaceHold {
  protected void loadResource(String name) throws TaskException {
    Properties props = new Properties();
    getContext().debug("Resource Loading " + name);
    try {
      ClassLoader classLoader = null;
      if (m_classpath != null) {
        final URL[] urls = PathUtil.toURLs(m_classpath, getContext());
        classLoader = new URLClassLoader(urls);
      } else {
        classLoader = ClassLoader.getSystemClassLoader();
      }
      final InputStream is = classLoader.getResourceAsStream(name);
      if (is != null) {
        props.load(is);
        addProperties(props);
      } else {
        getContext().warn("Unable to find resource " + name);
      }
    } catch (IOException ex) {
      throw new TaskException("Error", ex);
    }
  }
}
