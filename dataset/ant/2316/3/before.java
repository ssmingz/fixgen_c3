class PlaceHold {
  protected int runCmd(Commandline cmd, ExecuteStreamHandler out) {
    try {
      Project aProj = getProject();
      Execute exe = new Execute(out);
      exe.setAntRun(aProj);
      exe.setWorkingDirectory(aProj.getBaseDir());
      exe.setCommandline(cmd.getCommandline());
      return exe.execute();
    } catch (IOException e) {
      String msg = (("Failed executing: " + cmd.toString()) + ". Exception: ") + e.getMessage();
      throw new BuildException(msg, location);
    }
  }
}
