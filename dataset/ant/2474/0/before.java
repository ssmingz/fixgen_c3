class PlaceHold {
  public void execute() throws BuildException {
    if (defaultSetDefined || (defaultSet.getDir(getProject()) == null)) {
      try {
        super.execute();
      } finally {
        if (defaultSetDefined && (defaultSet.getDir(getProject()) != null)) {
          filesets.removeElement(defaultSet);
        }
      }
    } else if (isValidOs()) {
      Execute execute = prepareExec();
      Commandline cloned = ((Commandline) (cmdl.clone()));
      cloned.createArgument().setValue(defaultSet.getDir(getProject()).getPath());
      try {
        execute.setCommandline(cloned.getCommandline());
        runExecute(execute);
      } catch (IOException e) {
        throw new BuildException("Execute failed: " + e, e, location);
      } finally {
        logFlush();
      }
    }
  }
}
