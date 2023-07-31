class PlaceHold {
  public String getProperty(String propertyName) {
    return ((String) (PropertyHelper.getPropertyHelper(this).getProperty(propertyName)));
  }
}
