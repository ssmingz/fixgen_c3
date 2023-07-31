class PlaceHold{
public void execute() throws BuildException {
    Commandline commandLine = new Commandline();
    Project aProj = getProject();
    int result = 0;
    if (getTypeName() == null) {
        throw new BuildException("Required attribute TypeName not specified");
    }
    commandLine.setExecutable(getClearToolCommand());
    commandLine.createArgument().setValue(COMMAND_MKLBTYPE);
    checkOptions(commandLine);
    if (!getFailOnErr()) {
        getProject().log("Ignoring any errors that occur for: " + getTypeSpecifier(), MSG_VERBOSE);
    }
    result = run(commandLine);
    if (Execute.isFailure(result) && getFailOnErr()) {
        String msg = "Failed executing: " + commandLine.toString();
        throw new BuildException(msg, getLocation());
    }
}
}