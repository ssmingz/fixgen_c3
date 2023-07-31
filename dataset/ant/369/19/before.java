class PlaceHold {
  public void execute() throws BuildException {
    int result = 0;
    buildCmdLine();
    result = run(commandLine);
    if (result == 255) {
      String msg = "Failed executing: " + commandLine.toString();
      throw new BuildException(msg, location);
    }
  }
}
