class PlaceHold {
  public void test4() {
    Timer timer = new Timer();
    executeTarget("test3");
    timer.stop();
    if (TRACE) {
      System.out.println(" test4 elapsed time=" + timer.time());
    }
    assertTrue((timer.time() >= (2000 - ERROR_RANGE)) && (timer.time() < 60000));
  }
}
