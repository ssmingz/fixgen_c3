class PlaceHold {
  private void touchFile(String targetName, long timestamp) {
    buildRule.executeTarget(targetName);
    long time = getTargetTime();
    assertTimesNearlyMatch(timestamp, time);
  }
}
