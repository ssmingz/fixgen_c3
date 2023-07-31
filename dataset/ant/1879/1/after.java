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
      final Execute exe = new Execute();
      exe.setCommandline(cmdl);
      exe.setReturnCode(0);
      exe.execute(getContext());
    } finally {
      if ((m_inputFile == null) && (paramfile != null)) {
        paramfile.delete();
      }
    }
  }
}
