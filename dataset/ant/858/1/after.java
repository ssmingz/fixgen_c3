class PlaceHold {
  private int executeAsForked(JUnitTest test, ExecuteWatchdog watchdog, File casesFile)
      throws BuildException {
    if (perm != null) {
      log("Permissions ignored when running in forked mode!", MSG_WARN);
    }
    CommandlineJava cmd = ((CommandlineJava) (getCommandline().clone()));
    cmd.setClassname("org.apache.tools.ant.taskdefs.optional.junit.JUnitTestRunner");
    if (casesFile == null) {
      cmd.createArgument().setValue(test.getName());
    } else {
      log("Running multiple tests in the same VM", MSG_VERBOSE);
      cmd.createArgument().setValue("testsfile=" + casesFile);
    }
    cmd.createArgument().setValue("filtertrace=" + test.getFiltertrace());
    cmd.createArgument().setValue("haltOnError=" + test.getHaltonerror());
    cmd.createArgument().setValue("haltOnFailure=" + test.getHaltonfailure());
    if (includeAntRuntime) {
      Vector v = Execute.getProcEnvironment();
      Enumeration e = v.elements();
      while (e.hasMoreElements()) {
        String s = ((String) (e.nextElement()));
        if (s.startsWith("CLASSPATH=")) {
          cmd.createClasspath(getProject())
              .createPath()
              .append(new Path(getProject(), s.substring(10)));
        }
      }
      log(("Implicitly adding " + antRuntimeClasses) + " to CLASSPATH", MSG_VERBOSE);
      cmd.createClasspath(getProject()).createPath().append(antRuntimeClasses);
    }
    if (summary) {
      log("Running " + test.getName(), MSG_INFO);
      cmd.createArgument()
          .setValue(
              "formatter"
                  + "=org.apache.tools.ant.taskdefs.optional.junit.SummaryJUnitResultFormatter");
    }
    cmd.createArgument().setValue("showoutput=" + String.valueOf(showOutput));
    StringBuffer formatterArg = new StringBuffer(STRING_BUFFER_SIZE);
    final FormatterElement[] feArray = mergeFormatters(test);
    for (int i = 0; i < feArray.length; i++) {
      FormatterElement fe = feArray[i];
      if (fe.shouldUse(this)) {
        formatterArg.append("formatter=");
        formatterArg.append(fe.getClassname());
        File outFile = getOutput(fe, test);
        if (outFile != null) {
          formatterArg.append(",");
          formatterArg.append(outFile);
        }
        cmd.createArgument().setValue(formatterArg.toString());
        formatterArg = new StringBuffer();
      }
    }
    File propsFile = createTempPropertiesFile("junit");
    cmd.createArgument().setValue("propsfile=" + propsFile.getAbsolutePath());
    Hashtable p = getProject().getProperties();
    Properties props = new Properties();
    for (Enumeration e = p.keys(); e.hasMoreElements(); ) {
      Object key = e.nextElement();
      props.put(key, p.get(key));
    }
    try {
      FileOutputStream outstream = new FileOutputStream(propsFile);
      props.store(outstream, "Ant JUnitTask generated properties file");
      outstream.close();
    } catch (IOException e) {
      propsFile.delete();
      throw new BuildException("Error creating temporary properties " + "file.", e, getLocation());
    }
    Execute execute =
        new Execute(new LogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN), watchdog);
    execute.setCommandline(cmd.getCommandline());
    execute.setAntRun(getProject());
    if (dir != null) {
      execute.setWorkingDirectory(dir);
    }
    String[] environment = env.getVariables();
    if (environment != null) {
      for (int i = 0; i < environment.length; i++) {
        log("Setting environment variable: " + environment[i], MSG_VERBOSE);
      }
    }
    execute.setNewenvironment(newEnvironment);
    execute.setEnvironment(environment);
    log(cmd.describeCommand(), MSG_VERBOSE);
    int retVal;
    try {
      retVal = execute.execute();
    } catch (IOException e) {
      throw new BuildException("Process fork failed.", e, getLocation());
    } finally {
      if ((watchdog != null) && watchdog.killedProcess()) {
        logTimeout(feArray, test);
      }
      if (!propsFile.delete()) {
        throw new BuildException("Could not delete temporary " + "properties file.");
      }
    }
    return retVal;
  }
}
