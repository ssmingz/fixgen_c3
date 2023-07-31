class PlaceHold {
  public void execute() throws BuildException {
    String savedCommand = getCommand();
    if ((this.getCommand() == null) && (vecCommandlines.size() == 0)) {
      this.setCommand(AbstractCvsTask.DEFAULT_COMMAND);
    }
    String c = this.getCommand();
    Commandline cloned = null;
    if (c != null) {
      cloned = ((Commandline) (cmd.clone()));
      cloned.createArgument(true).setLine(c);
      this.addConfiguredCommandline(cloned, true);
    }
    try {
      for (int i = 0; i < vecCommandlines.size(); i++) {
        this.runCommand(((Commandline) (vecCommandlines.elementAt(i))));
      }
    } finally {
      if (cloned != null) {
        removeCommandline(cloned);
      }
      setCommand(savedCommand);
      if (outputStream != null) {
        try {
          outputStream.close();
        } catch (IOException e) {
        }
      }
      if (errorStream != null) {
        try {
          errorStream.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
