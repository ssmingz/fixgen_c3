class PlaceHold {
  public int getCalendarField() {
    String key = getValue().toLowerCase();
    Integer i = ((Integer) (calendarFields.get(key)));
    return i.intValue();
  }
}
