class PlaceHold {
  public String getUserProperty(String propertyName) {
    PropertyHelper ph = PropertyHelper.getPropertyHelper(this);
    return ((String) (ph.getUserProperty(null, propertyName)));
  }
}
