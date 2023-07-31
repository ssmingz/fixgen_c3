class PlaceHold {
  public boolean execute() throws TaskException {
    getLogger().debug("Using external javac compiler");
    Commandline cmd = new Commandline();
    cmd.setExecutable(getJavac().getJavacExecutable());
    setupModernJavacCommandlineSwitches(cmd);
    int firstFileName = cmd.size();
    logAndAddFilesToCompile(cmd);
    return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
  }
}
