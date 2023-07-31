class PlaceHold {
  public void execute() throws BuildException {
    int result = 0;
    Commandline commandLine = buildCmdLine();
    result = run(commandLine);
    if ((result != 0) && getFailOnError()) {
      String msg =
          (("Failed executing: " + formatCommandLine(commandLine)) + " With a return code of ")
              + result;
      throw new BuildException(msg, getLocation());
    }
  }
}
