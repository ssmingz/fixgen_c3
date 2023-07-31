class PlaceHold {
  public void execute() throws BuildException {
    checkOptions();
    File paramfile = createParamFile();
    try {
      Commandline cmdl = new Commandline();
      cmdl.setExecutable(findExecutable("jpcovmerge"));
      if (verbose) {
        cmdl.createArgument().setValue("-v");
      }
      cmdl.createArgument().setValue(getParamFileArgument() + paramfile.getAbsolutePath());
      if (isJProbe4Plus()) {
        cmdl.createArgument().setValue(tofile.getPath());
      }
      LogStreamHandler handler = new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN);
      Execute exec = new Execute(handler);
      log(cmdl.describeCommand(), MSG_VERBOSE);
      exec.setCommandline(cmdl.getCommandline());
      int exitValue = exec.execute();
      if (Execute.isFailure(exitValue)) {
        throw new BuildException(("JProbe Coverage Merging failed (" + exitValue) + ")");
      }
    } catch (IOException e) {
      throw new BuildException("Failed to run JProbe Coverage Merge: " + e);
    } finally {
      paramfile.delete();
    }
  }
}
