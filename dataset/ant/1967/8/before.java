class PlaceHold {
  public long getMultiplier() {
    String key = getValue().toLowerCase();
    Long l = ((Long) (timeTable.get(key)));
    return l.longValue();
  }
}
