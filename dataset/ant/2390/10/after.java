class PlaceHold {
  public void assertTimesNearlyMatch(long timestamp, long time) {
    long granularity = FILE_UTILS.getFileTimestampGranularity();
    assertTimesNearlyMatch(timestamp, time, granularity);
  }
}
