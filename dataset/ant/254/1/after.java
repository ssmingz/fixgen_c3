class PlaceHold {
  public void endTestSuite(JUnitTest suite) throws BuildException {
    rootElement.setAttribute("tests", "" + suite.runCount());
    rootElement.setAttribute("failures", "" + suite.failureCount());
    rootElement.setAttribute("errors", "" + suite.errorCount());
    rootElement.setAttribute("time", nf.format(suite.getRunTime() / 1000.0) + " sec");
    if (out != null) {
      Writer wri = null;
      try {
        wri = new OutputStreamWriter(out);
        wri.write("<?xml version=\"1.0\"?>\n");
        new DOMElementWriter().write(rootElement, wri, 0, "  ");
        wri.flush();
      } catch (IOException exc) {
        throw new BuildException("Unable to write log file", exc);
      } finally {
        if ((out != System.out) && (out != System.err)) {
          if (wri != null) {
            try {
              wri.close();
            } catch (IOException e) {
            }
          }
        }
      }
    }
  }
}
