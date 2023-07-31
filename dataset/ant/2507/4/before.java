class PlaceHold {
  public void endTestSuite(JUnitTest suite) throws BuildException {
    String newLine = System.getProperty("line.separator");
    StringBuffer sb = new StringBuffer("Tests run: ");
    sb.append(suite.runCount());
    sb.append(", Failures: ");
    sb.append(suite.failureCount());
    sb.append(", Errors: ");
    sb.append(suite.errorCount());
    sb.append(", Time elapsed: ");
    sb.append(nf.format(suite.getRunTime() / 1000.0));
    sb.append(" sec");
    sb.append(newLine);
    if (withOutAndErr) {
      if ((systemOutput != null) && (systemOutput.length() > 0)) {
        sb.append("Output:").append(newLine).append(systemOutput).append(newLine);
      }
      if ((systemError != null) && (systemError.length() > 0)) {
        sb.append("Error: ").append(newLine).append(systemError).append(newLine);
      }
    }
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
