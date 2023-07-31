class PlaceHold {
  public static void doReport(PrintStream out) {
    out.println("------- Ant diagnostics report -------");
    out.println(Main.getAntVersion());
    header(out, "Implementation Version");
    out.println("core tasks     : " + getImplementationVersion(Main.class));
    Class optional = null;
    try {
      optional = Class.forName(TEST_CLASS);
      out.println("optional tasks : " + getImplementationVersion(optional));
    } catch (ClassNotFoundException e) {
      out.println("optional tasks : not available");
    }
    header(out, "ANT_HOME/lib jar listing");
    doReportAntHomeLibraries(out);
    header(out, "USER_HOME/.ant/lib jar listing");
    doReportUserHomeLibraries(out);
    header(out, "Tasks availability");
    doReportTasksAvailability(out);
    header(out, "org.apache.env.Which diagnostics");
    doReportWhich(out);
    header(out, "XML Parser information");
    doReportParserInfo(out);
    header(out, "System properties");
    doReportSystemProperties(out);
    header(out, "Temp dir");
    doReportTempDir(out);
    out.println();
  }
}
