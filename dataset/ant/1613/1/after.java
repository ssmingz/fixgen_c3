class PlaceHold {
  @Test
  public void test3() {
    Timer timer = new Timer();
    buildRule.executeTarget("test3");
    timer.stop();
    assertTrue(timer.time() >= (2000 - ERROR_RANGE));
  }
}
