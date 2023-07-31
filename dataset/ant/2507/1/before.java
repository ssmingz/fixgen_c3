class PlaceHold {
  public void endTestSuite(JUnitTest suite) throws BuildException {
    rootElement.setAttribute(ATTR_TESTS, "" + suite.runCount());
    rootElement.setAttribute(ATTR_FAILURES, "" + suite.failureCount());
    rootElement.setAttribute(ATTR_ERRORS, "" + suite.errorCount());
    rootElement.setAttribute(ATTR_TIME, "" + (suite.getRunTime() / 1000.0));
    if (out != null) {
      Writer wri = null;
      try {
        wri = new BufferedWriter(new OutputStreamWriter(out, "UTF8"));
        wri.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        new DOMElementWriter().write(rootElement, wri, 0, "  ");
        wri.flush();
      } catch (IOException exc) {
        throw new BuildException("Unable to write log file", exc);
      } finally {
        if ((out != System.out) && (out != System.err)) {
          FileUtils.close(wri);
        }
      }
    }
  }
}
