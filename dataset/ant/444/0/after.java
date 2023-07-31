class PlaceHold {
  private TestResultHolder executeAsForked(JUnitTest test, ExecuteWatchdog watchdog, File casesFile)
      throws BuildException {
    if (perm != null) {
      log("Permissions ignored when running in forked mode!", MSG_WARN);
    }
    CommandlineJava cmd;
    try {
      cmd = ((CommandlineJava) (getCommandline().clone()));
    } catch (CloneNotSupportedException e) {
      throw new BuildException("This shouldn't happen", e, getLocation());
    }
    if (casesFile == null) {
      cmd.createArgument().setValue(test.getName());
      if (test.getMethods() != null) {
        cmd.createArgument().setValue(Constants.METHOD_NAMES + test.getMethodsString());
      }
    } else {
      log("Running multiple tests in the same VM", MSG_VERBOSE);
      cmd.createArgument().setValue(Constants.TESTSFILE + casesFile);
    }
    cmd.createArgument().setValue(Constants.SKIP_NON_TESTS + String.valueOf(test.isSkipNonTests()));
    cmd.createArgument().setValue(Constants.FILTERTRACE + test.getFiltertrace());
    cmd.createArgument().setValue(Constants.HALT_ON_ERROR + test.getHaltonerror());
    cmd.createArgument().setValue(Constants.HALT_ON_FAILURE + test.getHaltonfailure());
    checkIncludeAntRuntime(cmd);
    checkIncludeSummary(cmd);
    cmd.createArgument().setValue(Constants.SHOWOUTPUT + String.valueOf(showOutput));
    cmd.createArgument()
        .setValue(Constants.OUTPUT_TO_FORMATTERS + String.valueOf(outputToFormatters));
    cmd.createArgument().setValue(Constants.LOG_FAILED_TESTS + String.valueOf(logFailedTests));
    cmd.createArgument().setValue(Constants.THREADID + String.valueOf(test.getThread()));
    cmd.createArgument()
        .setValue(Constants.LOGTESTLISTENEREVENTS + String.valueOf(getEnableTestListenerEvents()));
    StringBuffer formatterArg = new StringBuffer(STRING_BUFFER_SIZE);
    final FormatterElement[] feArray = mergeFormatters(test);
    for (int i = 0; i < feArray.length; i++) {
      FormatterElement fe = feArray[i];
      if (fe.shouldUse(this)) {
        formatterArg.append(FORMATTER);
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
    File vmWatcher = createTempPropertiesFile("junitvmwatcher");
    cmd.createArgument().setValue(Constants.CRASHFILE + vmWatcher.getAbsolutePath());
    File propsFile = createTempPropertiesFile("junit");
    cmd.createArgument().setValue(Constants.PROPSFILE + propsFile.getAbsolutePath());
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
      FILE_UTILS.tryHardToDelete(propsFile);
      throw new BuildException("Error creating temporary properties " + "file.", e, getLocation());
    }
    Execute execute =
        new Execute(new JUnitLogStreamHandler(this, Project.MSG_INFO, Project.MSG_WARN), watchdog);
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
    checkForkedPath(cmd);
    TestResultHolder result = new TestResultHolder();
    try {
      result.exitCode = execute.execute();
    } catch (IOException e) {
      throw new BuildException("Process fork failed.", e, getLocation());
    } finally {
      String vmCrashString = "unknown";
      BufferedReader br = null;
      try {
        if (vmWatcher.exists()) {
          br = new BufferedReader(new FileReader(vmWatcher));
          vmCrashString = br.readLine();
        } else {
          vmCrashString =
              (("Monitor file (" + vmWatcher.getAbsolutePath())
                      + ") missing, location not writable,")
                  + " testcase not started or mixing ant versions?";
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        FileUtils.close(br);
        if (vmWatcher.exists()) {
          FILE_UTILS.tryHardToDelete(vmWatcher);
        }
      }
      boolean crash =
          ((watchdog != null) && watchdog.killedProcess())
              || (!TERMINATED_SUCCESSFULLY.equals(vmCrashString));
      if ((casesFile != null) && crash) {
        test = createDummyTestForBatchTest(test);
      }
      if ((watchdog != null) && watchdog.killedProcess()) {
        result.timedOut = true;
        logTimeout(feArray, test, vmCrashString);
      } else if (crash) {
        result.crashed = true;
        logVmCrash(feArray, test, vmCrashString);
      }
      if (!FILE_UTILS.tryHardToDelete(propsFile)) {
        throw new BuildException(
            (("Could not delete temporary " + "properties file '") + propsFile.getAbsolutePath())
                + "'.");
      }
    }
    return result;
  }
}
