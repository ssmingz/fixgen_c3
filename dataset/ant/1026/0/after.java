class PlaceHold {
  public void startTestSuite(JUnitTest suite) throws BuildException {
    if (out == null) {
      return;
    }
    StringBuffer sb = new StringBuffer("Testsuite: ");
    sb.append(suite.getName());
    sb.append(LINE_SEP);
    try {
      out.write(sb.toString().getBytes());
      out.flush();
    } catch (IOException ex) {
      throw new BuildException("Unable to write output", ex);
    }
  }
}
