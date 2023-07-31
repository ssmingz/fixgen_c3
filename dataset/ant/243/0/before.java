class PlaceHold {
  public boolean execute() throws TaskException {
    attributes.log("Using external javac compiler", MSG_VERBOSE);
    Commandline cmd = new Commandline();
    cmd.setExecutable(getJavac().getJavacExecutable());
    setupModernJavacCommandlineSwitches(cmd);
    int firstFileName = cmd.size();
    logAndAddFilesToCompile(cmd);
    return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
  }
}
