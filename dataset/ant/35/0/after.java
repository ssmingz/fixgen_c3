class PlaceHold{
public void execute() throws BuildException {
    Commandline toExecute = new Commandline();
    toExecute.setExecutable("cvs");
    if (cvsRoot != null) {
        toExecute.createArgument().setValue("-d");
        toExecute.createArgument().setValue(cvsRoot);
    }
    if (noexec) {
        toExecute.createArgument().setValue("-n");
    }
    if (quiet) {
        toExecute.createArgument().setValue("-q");
    }
    toExecute.createArgument().setLine(command);
    toExecute.addArguments(cmd.getCommandline());
    if (pack != null) {
        toExecute.createArgument().setValue(pack);
    }
    Execute exe = new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN), null);
    exe.setAntRun(project);
    if (dest == null) {
        dest = project.getBaseDir();
    }
    exe.setWorkingDirectory(dest);
    exe.setCommandline(toExecute.getCommandline());
    try {
        exe.execute();
    } catch (IOException e) {
        throw new BuildException(e, location);
    }
}
}