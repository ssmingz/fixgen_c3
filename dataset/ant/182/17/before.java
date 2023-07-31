class PlaceHold {
  public void execute() throws TaskException {
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
    Environment env = new Environment();
    if (port > 0) {
      Environment.Variable var = new Environment.Variable();
      var.setKey("CVS_CLIENT_PORT");
      var.setValue(String.valueOf(port));
      env.addVariable(var);
    }
    if (passFile != null) {
      Environment.Variable var = new Environment.Variable();
      var.setKey("CVS_PASSFILE");
      var.setValue(String.valueOf(passFile));
      env.addVariable(var);
    }
    if (cvsRsh != null) {
      Environment.Variable var = new Environment.Variable();
      var.setKey("CVS_RSH");
      var.setValue(String.valueOf(cvsRsh));
      env.addVariable(var);
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
          throw new TaskException(e.toString(), e);
        }
      } else {
        outputstream = new LogOutputStream(this, Project.MSG_INFO);
      }
      if (error != null) {
        try {
          errorstream = new PrintStream(new BufferedOutputStream(new FileOutputStream(error)));
        } catch (IOException e) {
          throw new TaskException(e.toString(), e);
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
    exe.setEnvironment(env.getVariables());
    try {
      int retCode = exe.execute();
      if (failOnError && (retCode != 0)) {
        throw new TaskException("cvs exited with error code " + retCode);
      }
    } catch (IOException e) {
      throw new TaskException(e.toString(), e);
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
