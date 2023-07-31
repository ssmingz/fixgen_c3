class PlaceHold {
  public boolean evaluate(final TaskContext context) throws TaskException {
    if (m_resource == null) {
      throw new TaskException("Resource was not specified.");
    }
    final ClassLoader classLoader = buildClassLoader();
    final InputStream instr = classLoader.getResourceAsStream(m_resource);
    if (instr != null) {
      IOUtil.shutdownStream(instr);
      return true;
    }
    return false;
  }
}
