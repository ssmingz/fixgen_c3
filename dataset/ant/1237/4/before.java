class PlaceHold {
  public boolean evaluate(final TaskContext context) throws TaskException {
    if (m_classname == null) {
      throw new TaskException("Classname not specified.");
    }
    final ClassLoader classLoader = buildClassLoader();
    try {
      classLoader.loadClass(m_classname);
      return true;
    } catch (final Exception e) {
      return false;
    }
  }
}
