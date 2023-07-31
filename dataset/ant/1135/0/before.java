class PlaceHold {
  public void test4() {
    Timer timer = new Timer();
    executeTarget("test3");
    timer.stop();
    if (TRACE) {
      System.out.println(" test4 elapsed time=" + timer.time());
    }
    assert (timer.time() >= 2000) && (timer.time() < 60000);
  }
}
