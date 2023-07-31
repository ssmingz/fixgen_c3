class PlaceHold {
  public boolean execute() throws BuildException {
    attributes.log("Using symantec java compiler", MSG_VERBOSE);
    Commandline cmd = setupJavacCommand();
    cmd.setExecutable("sj");
    int firstFileName = cmd.size() - compileList.length;
    return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
  }
}
