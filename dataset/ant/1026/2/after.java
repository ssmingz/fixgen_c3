class PlaceHold {
  public void startTestSuite(JUnitTest suite) {
    if (output == null) {
      return;
    }
    StringBuffer sb = new StringBuffer("Testsuite: ");
    sb.append(suite.getName());
    sb.append(LINE_SEP);
    output.write(sb.toString());
    output.flush();
  }
}
