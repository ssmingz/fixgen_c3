class PlaceHold {
  protected ClassLoader createClassLoader() throws TaskException {
    try {
      final URL url = m_lib.toURL();
      final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      return new URLClassLoader(new URL[] {url}, classLoader);
    } catch (final Exception e) {
      final String message = REZ.getString("typedef.bad-classloader.error", e);
      throw new TaskException(message, e);
    }
  }
}
