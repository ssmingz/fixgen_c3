class PlaceHold {
  public static Resource[] selectOutOfDateSources(
      ProjectComponent logTo, Resource[] source, FileNameMapper mapper, ResourceFactory targets) {
    return selectOutOfDateSources(
        logTo, source, mapper, targets, FILE_UTILS.getFileTimestampGranularity());
  }
}
