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
      final Execute exe = new Execute();
      exe.setCommandline(cmdl);
      exe.setReturnCode(0);
      exe.execute(getContext());
    } finally {
      paramfile.delete();
    }
  }
}
