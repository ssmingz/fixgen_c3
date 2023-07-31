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
        toExecute.createArgument().setLine(pack);
    }
    ExecuteStreamHandler streamhandler = null;
    OutputStream outputstream = null;
    OutputStream errorstream = null;
    if ((error == null) && (output == null)) {
        streamhandler = new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN);
    } else {
        if (output != null) {
            try {
                outputstream = new PrintStream(new BufferedOutputStream(new FileOutputStream(output)));
            } catch (IOException e) {
                throw new BuildException(e, location);
            }
        } else {
            outputstream = new LogOutputStream(this, Project.MSG_INFO);
        }
        if (error != null) {
            try {
                errorstream = new PrintStream(new BufferedOutputStream(new FileOutputStream(error)));
            } catch (IOException e) {
                throw new BuildException(e, location);
            }
        } else {
            errorstream = new LogOutputStream(this, Project.MSG_WARN);
        }
        streamhandler = new PumpStreamHandler(outputstream, errorstream);
    }
    Execute exe = new Execute(streamhandler, null);
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
    } finally {
        if (output != null) {
            try {
                outputstream.close();
            } catch (IOException e) {
            }
        }
        if (error != null) {
            try {
                errorstream.close();
            } catch (IOException e) {
            }
        }
    }
}
}