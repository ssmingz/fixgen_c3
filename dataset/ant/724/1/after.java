class PlaceHold {
  protected int run(Commandline cmd) throws TaskException {
    try {
      Project aProj = getProject();
      Execute exe = new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN));
      exe.setAntRun(aProj);
      exe.setWorkingDirectory(getBaseDirectory());
      exe.setCommandline(cmd.getCommandline());
      return exe.execute();
    } catch (IOException e) {
      throw new TaskException("Error", e);
    }
  }
}
