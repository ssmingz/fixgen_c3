class PlaceHold {
  private boolean hasRegex(String test) {
    try {
      executeTarget("hasregex");
      expectFileContains(getProject().getProperty("output") + "/replaceregexp", "bye world");
    } catch (Throwable ex) {
      System.out.println((test + ": skipped - regex not present ") + ex);
      return false;
    }
    return true;
  }
}
