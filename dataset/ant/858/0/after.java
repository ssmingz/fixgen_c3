class PlaceHold {
  protected void execute(List tests) throws BuildException {
    JUnitTest test = null;
    File casesFile = createTempPropertiesFile("junittestcases");
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(new BufferedWriter(new FileWriter(casesFile)));
      Iterator iter = tests.iterator();
      while (iter.hasNext()) {
        test = ((JUnitTest) (iter.next()));
        writer.print(test.getName());
        if (test.getTodir() == null) {
          writer.print("," + getProject().resolveFile("."));
        } else {
          writer.print("," + test.getTodir());
        }
        if (test.getOutfile() == null) {
          writer.println(("," + "TEST-") + test.getName());
        } else {
          writer.println("," + test.getOutfile());
        }
      }
      writer.flush();
      writer.close();
      writer = null;
      int exitValue = JUnitTestRunner.ERRORS;
      boolean wasKilled = false;
      ExecuteWatchdog watchdog = createWatchdog();
      exitValue = executeAsForked(test, watchdog, casesFile);
      if (watchdog != null) {
        wasKilled = watchdog.killedProcess();
      }
      actOnTestResult(exitValue, wasKilled, test, "Tests");
    } catch (IOException e) {
      log(e.toString(), MSG_ERR);
      throw new BuildException(e);
    } finally {
      if (writer != null) {
        writer.close();
      }
      try {
        casesFile.delete();
      } catch (Exception e) {
        log(e.toString(), MSG_ERR);
      }
    }
  }
}
