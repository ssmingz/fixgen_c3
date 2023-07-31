class PlaceHold {
  private int run(String[] command) throws BuildException {
    PumpStreamHandler psh =
        new PumpStreamHandler(
            new LogOutputStream(this, Project.MSG_INFO),
            new TeeOutputStream(new LogOutputStream(this, Project.MSG_WARN), bos));
    Execute exe = new Execute(psh, null);
    exe.setAntRun(getProject());
    if (workingdir != null) {
      exe.setWorkingDirectory(workingdir);
    }
    exe.setCommandline(command);
    try {
      return exe.execute();
    } catch (IOException e) {
      throw new BuildException(e, getLocation());
    } finally {
      FileUtils.close(bos);
    }
  }
}
