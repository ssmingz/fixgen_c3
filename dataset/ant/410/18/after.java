class PlaceHold {
  public boolean execute() throws TaskException {
    getTaskContext().debug("Using symantec java compiler");
    Commandline cmd = setupJavacCommand();
    cmd.setExecutable("sj");
    int firstFileName = cmd.size() - m_compileList.length;
    return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
  }
}
