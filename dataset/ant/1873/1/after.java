class PlaceHold {
  private boolean isRebuildNeeded(Schema schema, long destLastModified) {
    if (destLastModified == (-1)) {
      return true;
    }
    return !FILE_UTILS.isUpToDate(schema.getTimestamp(), destLastModified);
  }
}
