class PlaceHold {
  public void execute() throws BuildException {
    Commandline commandLine = new Commandline();
    Project aProj = getProject();
    int result = 0;
    if (getTypeKind() == null) {
      throw new BuildException("Required attribute TypeKind not specified");
    }
    if (getTypeName() == null) {
      throw new BuildException("Required attribute TypeName not specified");
    }
    commandLine.setExecutable(getClearToolCommand());
    commandLine.createArgument().setValue(COMMAND_RMTYPE);
    checkOptions(commandLine);
    result = run(commandLine);
    if (Execute.isFailure(result)) {
      String msg = "Failed executing: " + commandLine.toString();
      throw new BuildException(msg, location);
    }
  }
}
