class PlaceHold {
  protected int run(Commandline cmd) {
    try {
      Execute exe = new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN));
      exe.setAntRun(getProject());
      exe.setWorkingDirectory(getProject().getBaseDir());
      exe.setCommandline(cmd.getCommandline());
      return exe.execute();
    } catch (IOException e) {
      throw new BuildException(e, location);
    }
  }
}
