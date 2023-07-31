class PlaceHold {
  @Test
  public void testNow() {
    long now = System.currentTimeMillis();
    buildRule.executeTarget("testNow");
    long time = getTargetTime();
    assertTimesNearlyMatch(time, now, 5000);
  }
}
