class PlaceHold {
  public void startTestSuite(JUnitTest suite) {
    if (output == null) {
      return;
    }
    String newLine = System.getProperty("line.separator");
    StringBuffer sb = new StringBuffer("Testsuite: ");
    sb.append(suite.getName());
    sb.append(newLine);
    output.write(sb.toString());
    output.flush();
  }
}
