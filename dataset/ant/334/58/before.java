class PlaceHold {
  protected int run(Commandline cmd, ExecuteStreamHandler handler) {
    try {
      Execute exe = new Execute(handler);
      exe.setAntRun(getProject());
      exe.setWorkingDirectory(getProject().getBaseDir());
      exe.setCommandline(cmd.getCommandline());
      return exe.execute();
    } catch (IOException e) {
      throw new BuildException(e);
    }
  }
}
