class PlaceHold {
  public void execute() throws TaskException {
    File paramfile = null;
    if (m_inputFile == null) {
      checkOptions();
      paramfile = createParamFile();
    } else {
      paramfile = m_inputFile;
    }
    try {
      cmdl.setExecutable(new File(m_home, "jplauncher").getAbsolutePath());
      cmdl.addArgument("-jp_input=" + paramfile.getAbsolutePath());
      final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
      final Execute exe = new Execute(execManager);
      getContext().debug(cmdl.toString());
      exe.setCommandline(cmdl);
      int exitValue = exe.execute();
      if (exitValue != 0) {
        throw new TaskException(("JProbe Coverage failed (" + exitValue) + ")");
      }
    } catch (IOException e) {
      throw new TaskException("Failed to execute JProbe Coverage.", e);
    } finally {
      if ((m_inputFile == null) && (paramfile != null)) {
        paramfile.delete();
      }
    }
  }
}
