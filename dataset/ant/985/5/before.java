class PlaceHold {
  public void execute() throws TaskException {
    checkOptions();
    File paramfile = createParamFile();
    try {
      Commandline cmdl = new Commandline();
      cmdl.setExecutable(new File(home, "jpcovmerge").getAbsolutePath());
      if (verbose) {
        cmdl.createArgument().setValue("-v");
      }
      cmdl.createArgument().setValue("-jp_paramfile=" + paramfile.getAbsolutePath());
      final Execute exe = new Execute();
      exe.setOutput(new LogOutputStream(this, Project.MSG_INFO));
      exe.setError(new LogOutputStream(this, Project.MSG_WARN));
      log(cmdl.toString(), MSG_VERBOSE);
      exe.setCommandline(cmdl.getCommandline());
      int exitValue = exe.execute();
      if (exitValue != 0) {
        throw new TaskException(("JProbe Coverage Merging failed (" + exitValue) + ")");
      }
    } catch (IOException e) {
      throw new TaskException("Failed to run JProbe Coverage Merge: " + e);
    } finally {
      paramfile.delete();
    }
  }
}
