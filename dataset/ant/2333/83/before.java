class PlaceHold {
  public void testNow() {
    long now = System.currentTimeMillis();
    executeTarget("testNow");
    long time = getTargetTime();
    assertTimesNearlyMatch(time, now, 5000);
  }
}
