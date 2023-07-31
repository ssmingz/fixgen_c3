class PlaceHold {
  private int executeInVM(JUnitTest arg) throws BuildException {
    JUnitTest test = ((JUnitTest) (arg.clone()));
    test.setProperties(getProject().getProperties());
    if (dir != null) {
      log("dir attribute ignored if running in the same VM", MSG_WARN);
    }
    if (newEnvironment || (null != env.getVariables())) {
      log("Changes to environment variables are ignored if running in " + "the same VM.", MSG_WARN);
    }
    if (commandline.getBootclasspath() != null) {
      log("bootclasspath is ignored if running in the same VM.", MSG_WARN);
    }
    CommandlineJava.SysProperties sysProperties = commandline.getSystemProperties();
    if (sysProperties != null) {
      sysProperties.setSystem();
    }
    try {
      log("Using System properties " + System.getProperties(), MSG_VERBOSE);
      createClassLoader();
      if (classLoader != null) {
        classLoader.setThreadContextLoader();
      }
      runner =
          new JUnitTestRunner(
              test,
              test.getHaltonerror(),
              test.getFiltertrace(),
              test.getHaltonfailure(),
              classLoader);
      if (summary) {
        log("Running " + test.getName(), MSG_INFO);
        SummaryJUnitResultFormatter f = new SummaryJUnitResultFormatter();
        f.setWithOutAndErr("withoutanderr".equalsIgnoreCase(summaryValue));
        f.setOutput(getDefaultOutput());
        runner.addFormatter(f);
      }
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
      return runner.getRetCode();
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
