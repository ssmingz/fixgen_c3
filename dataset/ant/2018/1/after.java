class PlaceHold {
  public boolean execute() throws BuildException {
    attributes.log("Using symantec java compiler", MSG_VERBOSE);
    Commandline cmd = setupJavacCommand();
    String exec = getJavac().getExecutable();
    cmd.setExecutable(exec == null ? "sj" : exec);
    int firstFileName = cmd.size() - compileList.length;
    return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
  }
}
