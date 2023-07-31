class PlaceHold {
  private void touchFile(String targetName, long timestamp) {
    executeTarget(targetName);
    long time = getTargetTime();
    assertTimesNearlyMatch(timestamp, time);
  }
}
