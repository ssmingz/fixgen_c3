class PlaceHold {
  private int run(String[] command) throws BuildException {
    Execute exe = new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN), null);
    exe.setAntRun(project);
    if (workingdir != null) {
      exe.setWorkingDirectory(workingdir);
    }
    exe.setCommandline(command);
    try {
      return exe.execute();
    } catch (IOException e) {
      throw new BuildException(e);
    }
  }
}
