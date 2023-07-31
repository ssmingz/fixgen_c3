class PlaceHold {
  protected void validate() throws TaskException {
    if ((m_metamataHome == null) || (!m_metamataHome.exists())) {
      throw new TaskException("'metamatahome' must point to Metamata home directory.");
    }
    m_metamataHome = resolveFile(m_metamataHome.getPath());
    File jar = getMetamataJar(m_metamataHome);
    if (!jar.exists()) {
      throw new TaskException(jar + " does not exist. Check your metamata installation.");
    }
  }
}
