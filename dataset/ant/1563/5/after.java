class PlaceHold {
  public void execute() throws BuildException {
    File paramfile = null;
    if (inputFile == null) {
      checkOptions();
      paramfile = createParamFile();
    } else {
      paramfile = inputFile;
    }
    try {
      cmdl.setExecutable(findExecutable("jplauncher"));
      cmdl.createArgument().setValue("-jp_input=" + paramfile.getAbsolutePath());
      LogStreamHandler handler = new CoverageStreamHandler(this);
      Execute exec = new Execute(handler);
      log(cmdl.describeCommand(), MSG_VERBOSE);
      exec.setCommandline(cmdl.getCommandline());
      int exitValue = exec.execute();
      if (Execute.isFailure(exitValue)) {
        throw new BuildException(("JProbe Coverage failed (" + exitValue) + ")");
      }
    } catch (IOException e) {
      throw new BuildException("Failed to execute JProbe Coverage.", e);
    } finally {
      if ((inputFile == null) && (paramfile != null)) {
        paramfile.delete();
      }
    }
  }
}
