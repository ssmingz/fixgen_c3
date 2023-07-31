class PlaceHold {
  public int executeJava() throws TaskException {
    final String classname = m_cmdl.getClassname();
    final String jar = m_cmdl.getJar();
    if ((classname != null) && (jar != null)) {
      throw new TaskException("Only one of Classname and Jar can be set.");
    } else if ((classname == null) && (jar == null)) {
      throw new TaskException("Classname must not be null.");
    }
    if ((!m_fork) && (jar != null)) {
      throw new TaskException("Cannot execute a jar in non-forked mode. Please set fork='true'. ");
    }
    if (m_fork) {
      getLogger().debug("Forking " + m_cmdl.toString());
      return run(new Commandline(m_cmdl.getCommandline()));
    } else {
      if (m_cmdl.getVmCommand().size() > 1) {
        getLogger().warn("JVM args ignored when same JVM is used.");
      }
      if (m_dir != null) {
        getLogger().warn("Working directory ignored when same JVM is used.");
      }
      getLogger().debug("Running in same VM " + m_cmdl.getJavaCommand().toString());
      run(m_cmdl);
      return 0;
    }
  }
}
