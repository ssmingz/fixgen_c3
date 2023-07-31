class PlaceHold {
  public void execute() throws BuildException {
    boolean errorOccurred = false;
    boolean failureOccurred = false;
    final String oldclasspath = System.getProperty("java.class.path");
    commandline.createClasspath(project).createPathElement().setPath(oldclasspath);
    Enumeration list = tests.elements();
    while (list.hasMoreElements()) {
      final JUnitTest test = ((JUnitTest) (list.nextElement()));
      final String filename = ("TEST-" + test.getName()) + ".xml";
      project.log("Running " + test.getName());
      if (defaultOutfile && ((test.getOutfile() == null) || (test.getOutfile().length() == 0))) {
        test.setOutfile(filename);
      }
      int exitValue = 2;
      if (test.getFork()) {
        try {
          final Execute execute = new Execute(new PumpStreamHandler(), createWatchdog());
          final Commandline cmdl = new Commandline();
          cmdl.addLine(commandline.getCommandline());
          cmdl.addLine(test.getCommandline());
          execute.setCommandline(cmdl.getCommandline());
          log("Execute JUnit: " + cmdl, MSG_VERBOSE);
          exitValue = execute.execute();
        } catch (IOException e) {
          throw new BuildException("Process fork failed.", e, location);
        }
      } else {
        final Object[] arg = new Object[] {test};
        final Class[] argType = new Class[] {arg[0].getClass()};
        try {
          final Class target =
              Class.forName("org.apache.tools.ant.taskdefs.optional.junit.JUnitTestRunner");
          final Method main = target.getMethod("runTest", argType);
          project.log("Load JUnit: " + test, MSG_VERBOSE);
          exitValue = ((Integer) (main.invoke(null, arg))).intValue();
        } catch (InvocationTargetException e) {
          Throwable t = e.getTargetException();
          String msg = "Running test failed: " + t.getMessage();
          throw new BuildException(msg, t, location);
        } catch (Exception e) {
          String msg = "Running test failed: " + e.getMessage();
          throw new BuildException(msg, e, location);
        }
      }
      boolean errorOccurredHere = exitValue == 2;
      boolean failureOccurredHere = exitValue == 1;
      if ((errorOccurredHere && test.getHaltonerror())
          || (failureOccurredHere && test.getHaltonfailure())) {
        throw new BuildException("JUNIT FAILED", location);
      } else if (errorOccurredHere || failureOccurredHere) {
        log("JUNIT FAILED", MSG_ERR);
      }
      errorOccurred = errorOccurred || errorOccurredHere;
      failureOccurred = failureOccurred || failureOccurredHere;
    }
  }
}
