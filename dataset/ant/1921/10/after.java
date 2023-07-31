class PlaceHold {
  public void execute() throws BuildException {
    checkOptions();
    try {
      Commandline cmdl = new Commandline();
      cmdl.setExecutable(findExecutable("jpcovreport"));
      String[] params = getParameters();
      for (int i = 0; i < params.length; i++) {
        cmdl.createArgument().setValue(params[i]);
      }
      LogStreamHandler handler = new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN);
      Execute exec = new Execute(handler);
      log(cmdl.describeCommand(), MSG_VERBOSE);
      exec.setCommandline(cmdl.getCommandline());
      int exitValue = exec.execute();
      if (Execute.isFailure(exitValue)) {
        throw new BuildException(("JProbe Coverage Report failed (" + exitValue) + ")");
      }
      log("coveragePath: " + coveragePath, MSG_VERBOSE);
      log("format: " + format, MSG_VERBOSE);
      if ((reference != null) && "xml".equals(format)) {
        reference.createEnhancedXMLReport();
      }
    } catch (IOException e) {
      throw new BuildException("Failed to execute JProbe Coverage Report.", e);
    }
  }
}
