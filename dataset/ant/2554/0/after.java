class PlaceHold {
  protected void runCommand(Commandline toExecute) throws BuildException {
    Environment env = new Environment();
    if (port > 0) {
      Environment.Variable var = new Environment.Variable();
      var.setKey("CVS_CLIENT_PORT");
      var.setValue(String.valueOf(port));
      env.addVariable(var);
    }
    if (passFile == null) {
      File defaultPassFile =
          new File(
              (System.getProperty("cygwin.user.home", System.getProperty("user.home"))
                      + File.separatorChar)
                  + ".cvspass");
      if (defaultPassFile.exists()) {
        this.setPassfile(defaultPassFile);
      }
    }
    if (passFile != null) {
      if (passFile.isFile() && passFile.canRead()) {
        Environment.Variable var = new Environment.Variable();
        var.setKey("CVS_PASSFILE");
        var.setValue(String.valueOf(passFile));
        env.addVariable(var);
        log("Using cvs passfile: " + String.valueOf(passFile), MSG_INFO);
      } else if (!passFile.canRead()) {
        log(
            ("cvs passfile: " + String.valueOf(passFile)) + " ignored as it is not readable",
            MSG_WARN);
      } else {
        log(
            ("cvs passfile: " + String.valueOf(passFile)) + " ignored as it is not a file",
            MSG_WARN);
      }
    }
    if (cvsRsh != null) {
      Environment.Variable var = new Environment.Variable();
      var.setKey("CVS_RSH");
      var.setValue(String.valueOf(cvsRsh));
      env.addVariable(var);
    }
    Execute exe = new Execute(getExecuteStreamHandler(), null);
    exe.setAntRun(getProject());
    if (dest == null) {
      dest = getProject().getBaseDir();
    }
    if (!dest.exists()) {
      dest.mkdirs();
    }
    exe.setWorkingDirectory(dest);
    exe.setCommandline(toExecute.getCommandline());
    exe.setEnvironment(env.getVariables());
    try {
      String actualCommandLine = executeToString(exe);
      log(actualCommandLine, MSG_VERBOSE);
      int retCode = exe.execute();
      log("retCode=" + retCode, MSG_DEBUG);
      if (failOnError && Execute.isFailure(retCode)) {
        throw new BuildException(
            (((("cvs exited with error code " + retCode) + StringUtils.LINE_SEP)
                        + "Command line was [")
                    + actualCommandLine)
                + "]",
            getLocation());
      }
    } catch (IOException e) {
      if (failOnError) {
        throw new BuildException(e, getLocation());
      } else {
        log("Caught exception: " + e.getMessage(), MSG_WARN);
      }
    } catch (BuildException e) {
      if (failOnError) {
        throw e;
      } else {
        Throwable t = e.getException();
        if (t == null) {
          t = e;
        }
        log("Caught exception: " + t.getMessage(), MSG_WARN);
      }
    } catch (Exception e) {
      if (failOnError) {
        throw new BuildException(e, getLocation());
      } else {
        log("Caught exception: " + e.getMessage(), MSG_WARN);
      }
    } finally {
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
