class PlaceHold {
  public String getProperty(String propertyName) {
    PropertyHelper ph = PropertyHelper.getPropertyHelper(this);
    return ((String) (ph.getProperty(null, propertyName)));
  }
}
