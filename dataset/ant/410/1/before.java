class PlaceHold {
  public boolean execute() throws TaskException {
    Commandline cmd;
    getLogger().debug("Using gcj compiler");
    cmd = setupGCJCommand();
    int firstFileName = cmd.size();
    logAndAddFilesToCompile(cmd);
    return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
  }
}
