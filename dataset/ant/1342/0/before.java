class PlaceHold {
  public void test3() {
    Timer timer = new Timer();
    executeTarget("test3");
    timer.stop();
    if (TRACE) {
      System.out.println(" test3 elapsed time=" + timer.time());
    }
    assert timer.time() >= (2000 - ERROR_RANGE);
  }
}
