class PlaceHold {
  private TestResultHolder executeInVM(JUnitTest arg) throws BuildException {
    JUnitTest test = ((JUnitTest) (arg.clone()));
    test.setProperties(getProject().getProperties());
    if (dir != null) {
      log("dir attribute ignored if running in the same VM", MSG_WARN);
    }
    if (newEnvironment || (null != env.getVariables())) {
      log("Changes to environment variables are ignored if running in " + "the same VM.", MSG_WARN);
    }
    if (getCommandline().getBootclasspath() != null) {
      log("bootclasspath is ignored if running in the same VM.", MSG_WARN);
    }
    CommandlineJava.SysProperties sysProperties = getCommandline().getSystemProperties();
    if (sysProperties != null) {
      sysProperties.setSystem();
    }
    try {
      log("Using System properties " + System.getProperties(), MSG_VERBOSE);
      if (splitJunit) {
        classLoader = ((AntClassLoader) (delegate.getClass().getClassLoader()));
      } else {
        createClassLoader();
      }
      if (classLoader != null) {
        classLoader.setThreadContextLoader();
      }
      runner =
          delegate.newJUnitTestRunner(
              test,
              test.getHaltonerror(),
              test.getFiltertrace(),
              test.getHaltonfailure(),
              false,
              true,
              classLoader);
      if (summary) {
        JUnitTaskMirror.SummaryJUnitResultFormatterMirror f =
            delegate.newSummaryJUnitResultFormatter();
        f.setWithOutAndErr("withoutanderr".equalsIgnoreCase(summaryValue));
        f.setOutput(getDefaultOutput());
        runner.addFormatter(f);
      }
      runner.setPermissions(perm);
      final FormatterElement[] feArray = mergeFormatters(test);
      for (int i = 0; i < feArray.length; i++) {
        FormatterElement fe = feArray[i];
        if (fe.shouldUse(this)) {
          File outFile = getOutput(fe, test);
          if (outFile != null) {
            fe.setOutfile(outFile);
          } else {
            fe.setOutput(getDefaultOutput());
          }
          runner.addFormatter(fe.createFormatter(classLoader));
        }
      }
      runner.run();
      TestResultHolder result = new TestResultHolder();
      result.exitCode = runner.getRetCode();
      return result;
    } finally {
      if (sysProperties != null) {
        sysProperties.restoreSystem();
      }
      if (classLoader != null) {
        classLoader.resetThreadContextLoader();
      }
    }
  }
}
