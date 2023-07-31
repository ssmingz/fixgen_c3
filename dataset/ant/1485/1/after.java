class PlaceHold {
  public void execute() throws TaskException {
    checkOptions();
    File paramfile = createParamFile();
    try {
      Commandline cmdl = new Commandline();
      cmdl.setExecutable(new File(home, "jpcovmerge").getAbsolutePath());
      if (verbose) {
        cmdl.addArgument("-v");
      }
      cmdl.addArgument("-jp_paramfile=" + paramfile.getAbsolutePath());
      final ExecManager execManager = ((ExecManager) (getService(ExecManager.class)));
      final Execute2 exe = new Execute2(execManager);
      setupLogger(exe);
      getLogger().debug(cmdl.toString());
      exe.setCommandline(cmdl);
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
