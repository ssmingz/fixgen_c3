class PlaceHold {
  public void execute() throws TaskException {
    Commandline toExecute = new Commandline();
    toExecute.setExecutable("rpm");
    if (topDir != null) {
      toExecute.createArgument().setValue("--define");
      toExecute.createArgument().setValue("_topdir" + topDir);
    }
    toExecute.createArgument().setLine(command);
    if (cleanBuildDir) {
      toExecute.createArgument().setValue("--clean");
    }
    if (removeSpec) {
      toExecute.createArgument().setValue("--rmspec");
    }
    if (removeSource) {
      toExecute.createArgument().setValue("--rmsource");
    }
    toExecute.createArgument().setValue("SPECS/" + specFile);
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
          throw new TaskException("Error", e);
        }
      } else {
        outputstream = new LogOutputStream(this, Project.MSG_INFO);
      }
      if (error != null) {
        try {
          errorstream = new PrintStream(new BufferedOutputStream(new FileOutputStream(error)));
        } catch (IOException e) {
          throw new TaskException("Error", e);
        }
      } else {
        errorstream = new LogOutputStream(this, Project.MSG_WARN);
      }
      streamhandler = new PumpStreamHandler(outputstream, errorstream);
    }
    Execute exe = new Execute(streamhandler, null);
    exe.setAntRun(project);
    if (topDir == null) {
      topDir = project.getBaseDir();
    }
    exe.setWorkingDirectory(topDir);
    exe.setCommandline(toExecute.getCommandline());
    try {
      exe.execute();
      getLogger().info(("Building the RPM based on the " + specFile) + " file");
    } catch (IOException e) {
      throw new TaskException("Error", e);
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
