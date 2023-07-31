class PlaceHold {
  public long getMilliseconds(int action) {
    String granularityU = getValue().toUpperCase(Locale.US);
    if ("".equals(granularityU)) {
      if (action == SEND_FILES) {
        return GRANULARITY_MINUTE;
      }
    } else if ("MINUTE".equals(granularityU)) {
      return GRANULARITY_MINUTE;
    }
    return 0L;
  }
}
