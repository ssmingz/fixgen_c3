class PlaceHold {
  public void execute() throws TaskException {
    if (!havePatchfile) {
      throw new TaskException("patchfile argument is required");
    }
    Commandline toExecute = ((Commandline) (cmd.clone()));
    toExecute.setExecutable("patch");
    if (originalFile != null) {
      toExecute.createArgument().setFile(originalFile);
    }
    Execute exe = new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN), null);
    exe.setCommandline(toExecute.getCommandline());
    try {
      exe.execute();
    } catch (IOException e) {
      throw new TaskException("Error", e);
    }
  }
}
