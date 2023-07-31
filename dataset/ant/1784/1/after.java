class PlaceHold {
  public void endTestSuite(JUnitTest suite) throws BuildException {
    StringBuffer sb = new StringBuffer("Tests run: ");
    sb.append(suite.runCount());
    sb.append(", Failures: ");
    sb.append(suite.failureCount());
    sb.append(", Errors: ");
    sb.append(suite.errorCount());
    sb.append(", Time elapsed: ");
    sb.append(nf.format(suite.getRunTime() / 1000.0));
    sb.append(" sec");
    sb.append(System.getProperty("line.separator"));
    try {
      out.write(sb.toString().getBytes());
      out.flush();
    } catch (IOException ioex) {
      throw new BuildException("Unable to write summary output", ioex);
    } finally {
      if ((out != System.out) && (out != System.err)) {
        try {
          out.close();
        } catch (IOException e) {
        }
      }
    }
  }
}
