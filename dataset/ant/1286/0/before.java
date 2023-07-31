class PlaceHold {
  protected void executeTarget(String targetName) {
    PrintStream sysOut = System.out;
    PrintStream sysErr = System.err;
    try {
      sysOut.flush();
      sysErr.flush();
      outBuffer = new StringBuffer();
      PrintStream out = new PrintStream(new AntOutputStream());
      System.setOut(out);
      errBuffer = new StringBuffer();
      PrintStream err = new PrintStream(new AntOutputStream());
      System.setErr(err);
      logBuffer = new StringBuffer();
      buildException = null;
      project.executeTarget(targetName);
    } finally {
      System.setOut(sysOut);
      System.setErr(sysErr);
    }
  }
}
