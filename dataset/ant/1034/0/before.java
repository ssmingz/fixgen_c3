class PlaceHold {
  private boolean hasRegex(String test) {
    try {
      executeTarget("hasregex");
      expectFileContains("result/replaceregexp", "bye world");
    } catch (Throwable ex) {
      System.out.println((test + ": skipped - regex not present ") + ex);
      return false;
    }
    return true;
  }
}
