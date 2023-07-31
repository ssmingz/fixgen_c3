class PlaceHold {
  public void execute() throws BuildException {
    if (defaultSetDefined || (defaultSet.getDir(project) == null)) {
      super.execute();
    } else if (isValidOs()) {
      createArg().setValue(defaultSet.getDir(project).getPath());
      Execute execute = prepareExec();
      try {
        execute.setCommandline(cmdl.getCommandline());
        runExecute(execute);
      } catch (IOException e) {
        throw new BuildException("Execute failed: " + e, e, location);
      } finally {
        logFlush();
      }
    }
  }
}
