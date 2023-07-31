class PlaceHold {
  protected void execute(List testList) throws BuildException {
    JUnitTest test = null;
    File casesFile = createTempPropertiesFile("junittestcases");
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(casesFile));
      log(("Creating casesfile '" + casesFile.getAbsolutePath()) + "' with content: ", MSG_VERBOSE);
      PrintStream logWriter = new PrintStream(new LogOutputStream(this, Project.MSG_VERBOSE));
      Iterator iter = testList.iterator();
      while (iter.hasNext()) {
        test = ((JUnitTest) (iter.next()));
        printDual(writer, logWriter, test.getName());
        if (test.getMethods() != null) {
          printDual(writer, logWriter, ":" + test.getMethodsString().replace(',', '+'));
        }
        if (test.getTodir() == null) {
          printDual(writer, logWriter, "," + getProject().resolveFile("."));
        } else {
          printDual(writer, logWriter, "," + test.getTodir());
        }
        if (test.getOutfile() == null) {
          printlnDual(writer, logWriter, ("," + "TEST-") + test.getName());
        } else {
          printlnDual(writer, logWriter, "," + test.getOutfile());
        }
      }
      writer.flush();
      writer.close();
      writer = null;
      ExecuteWatchdog watchdog = createWatchdog();
      TestResultHolder result = executeAsForked(test, watchdog, casesFile);
      actOnTestResult(result, test, "Tests");
    } catch (IOException e) {
      log(e.toString(), MSG_ERR);
      throw new BuildException(e);
    } finally {
      FileUtils.close(writer);
      try {
        FILE_UTILS.tryHardToDelete(casesFile);
      } catch (Exception e) {
        log(e.toString(), MSG_ERR);
      }
    }
  }
}
