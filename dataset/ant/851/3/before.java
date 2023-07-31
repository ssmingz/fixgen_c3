class PlaceHold {
  protected ClassLoader createClassLoader() throws TaskException {
    try {
      final URL url = m_lib.toURL();
      final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      return new URLClassLoader(new URL[] {url}, classLoader);
    } catch (final Exception e) {
      throw new TaskException("Failed to build classLoader due to: " + e, e);
    }
  }
}
