class PlaceHold {
  protected int run(Commandline cmd) {
    try {
      Project aProj = getProject();
      Execute exe = new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN));
      exe.setAntRun(aProj);
      exe.setWorkingDirectory(aProj.getBaseDir());
      exe.setCommandline(cmd.getCommandline());
      return exe.execute();
    } catch (IOException e) {
      throw new BuildException(e);
    }
  }
}
