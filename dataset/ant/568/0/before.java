class PlaceHold {
  public void assertTimesNearlyMatch(long timestamp, long time) {
    long granularity = FileUtils.newFileUtils().getFileTimestampGranularity();
    assertTimesNearlyMatch(timestamp, time, granularity);
  }
}
