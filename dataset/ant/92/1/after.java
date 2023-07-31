class PlaceHold{
public void execute() throws TaskException {
    if (defaultSetDefined || (defaultSet.getDir(getProject()) == null)) {
        super.execute();
    } else if (isValidOs()) {
        createArg().setValue(defaultSet.getDir(getProject()).getPath());
        Execute execute = prepareExec();
        try {
            execute.setCommandline(getCommand().getCommandline());
            runExecute(execute);
        } catch (IOException e) {
            throw new TaskException("Execute failed: " + e, e);
        } finally {
            logFlush();
        }
    }
}
}