class PlaceHold {
  public void test_getLocale() {
    final FontData data = new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    testPerformance(
        new Runnable() {
          public void run() {
            data.getLocale();
          }
        });
  }
}
