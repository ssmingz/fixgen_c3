class PlaceHold {
  @Test
  public void test4() {
    Timer timer = new Timer();
    buildRule.executeTarget("test3");
    timer.stop();
    assertTrue((timer.time() >= (2000 - ERROR_RANGE)) && (timer.time() < 60000));
  }
}
