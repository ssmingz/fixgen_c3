class PlaceHold {
  public void execute() throws TaskException {
    File paramfile = null;
    if (inputFile == null) {
      checkOptions();
      paramfile = createParamFile();
    } else {
      paramfile = inputFile;
    }
    try {
      cmdl.setExecutable(new File(home, "jplauncher").getAbsolutePath());
      cmdl.createArgument().setValue("-jp_input=" + paramfile.getAbsolutePath());
      LogStreamHandler handler = new CoverageStreamHandler(this);
      Execute exec = new Execute(handler);
      getLogger().debug(cmdl.toString());
      exec.setCommandline(cmdl.getCommandline());
      int exitValue = exec.execute();
      if (exitValue != 0) {
        throw new TaskException(("JProbe Coverage failed (" + exitValue) + ")");
      }
    } catch (IOException e) {
      throw new TaskException("Failed to execute JProbe Coverage.", e);
    } finally {
      if ((inputFile == null) && (paramfile != null)) {
        paramfile.delete();
      }
    }
  }
}
