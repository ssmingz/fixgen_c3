class PlaceHold {
  public int getCalendarField() {
    String key = getValue().toLowerCase(Locale.ENGLISH);
    Integer i = ((Integer) (calendarFields.get(key)));
    return i.intValue();
  }
}
