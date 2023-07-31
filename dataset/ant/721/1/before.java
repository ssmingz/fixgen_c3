class PlaceHold {
  private void checkIncludeSummary(CommandlineJava cmd) {
    if (summary) {
      String prefix = "";
      if ("withoutanderr".equalsIgnoreCase(summaryValue)) {
        prefix = "OutErr";
      }
      cmd.createArgument()
          .setValue(
              ((Constants.FORMATTER + "org.apache.tools.ant.taskdefs.optional.junit.") + prefix)
                  + "SummaryJUnitResultFormatter");
    }
  }
}
