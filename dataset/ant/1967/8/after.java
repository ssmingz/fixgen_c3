class PlaceHold {
  public long getMultiplier() {
    String key = getValue().toLowerCase(Locale.ENGLISH);
    Long l = ((Long) (timeTable.get(key)));
    return l.longValue();
  }
}
