class PlaceHold {
  private boolean isRebuildNeeded(Schema schema, long destLastModified) {
    if (destLastModified == (-1)) {
      return true;
    }
    return !fileutils.isUpToDate(schema.getTimestamp(), destLastModified);
  }
}
