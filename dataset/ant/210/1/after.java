class PlaceHold {
  protected void loadResource(String name) throws TaskException {
    Properties props = new Properties();
    getContext().debug("Resource Loading " + name);
    try {
      final ClassLoader classLoader = FileListUtil.createClassLoader(m_classpath, getContext());
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
