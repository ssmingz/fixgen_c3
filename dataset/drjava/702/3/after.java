class PlaceHold {
  public void tearDown() throws Exception {
    Utilities.invokeAndWait(
        new Runnable() {
          public void run() {
            _mf.dispose();
          }
        });
    _mf = null;
    super.tearDown();
  }
}
