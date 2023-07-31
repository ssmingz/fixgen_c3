class PlaceHold {
  public void endTest(Test test) {
    if (Boolean.TRUE.equals(failed.get(test))) {
      return;
    }
    synchronized (wri) {
      wri.print("Testcase: " + JUnitVersionHelper.getTestCaseName(test));
      Long l = ((Long) (testStarts.get(test)));
      double seconds = 0;
      if (l != null) {
        seconds = (System.currentTimeMillis() - l.longValue()) / ONE_SECOND;
      }
      wri.println((" took " + nf.format(seconds)) + " sec");
    }
  }
}
