class PlaceHold {
  public int executeJava() throws BuildException {
    String classname = cmdl.getClassname();
    if ((classname == null) && (cmdl.getJar() == null)) {
      throw new BuildException("Classname must not be null.");
    }
    if ((!fork) && (cmdl.getJar() != null)) {
      throw new BuildException(
          "Cannot execute a jar in non-forked mode." + " Please set fork='true'. ");
    }
    if (spawn && (!fork)) {
      throw new BuildException(
          "Cannot spawn a java process in non-forked mode." + " Please set fork='true'. ");
    }
    if (spawn && incompatibleWithSpawn) {
      getProject()
          .log(
              "spawn does not allow attributes related to input, " + "output, error, result",
              MSG_ERR);
      getProject().log("spawn does not also not allow timeout", MSG_ERR);
      throw new BuildException(
          "You have used an attribute which is " + "not compatible with spawn");
    }
    if (fork) {
      if (perm != null) {
        log("Permissions can not be set this way in forked mode.", MSG_WARN);
      }
      log(cmdl.describeCommand(), MSG_VERBOSE);
    } else {
      if (cmdl.getVmCommand().size() > 1) {
        log("JVM args ignored when same JVM is used.", MSG_WARN);
      }
      if (dir != null) {
        log("Working directory ignored when same JVM is used.", MSG_WARN);
      }
      if (newEnvironment || (null != env.getVariables())) {
        log("Changes to environment variables are ignored when same " + "JVM is used.", MSG_WARN);
      }
      if (cmdl.getBootclasspath() != null) {
        log("bootclasspath ignored when same JVM is used.", MSG_WARN);
      }
      log("Running in same VM " + cmdl.describeJavaCommand(), MSG_VERBOSE);
    }
    try {
      if (fork) {
        if (!spawn) {
          return fork(cmdl.getCommandline());
        } else {
          spawn(cmdl.getCommandline());
          return 0;
        }
      } else {
        try {
          run(cmdl);
          return 0;
        } catch (ExitException ex) {
          return ex.getStatus();
        }
      }
    } catch (BuildException e) {
      if (failOnError) {
        throw e;
      } else {
        log(e.getMessage(), MSG_ERR);
        return 0;
      }
    } catch (Throwable t) {
      if (failOnError) {
        throw new BuildException(t);
      } else {
        log(t.getMessage(), MSG_ERR);
        return 0;
      }
    }
  }
}
