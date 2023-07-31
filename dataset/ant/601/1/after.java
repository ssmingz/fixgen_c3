class PlaceHold {
  public String getUserProperty(String propertyName) {
    return ((String) (PropertyHelper.getPropertyHelper(this).getUserProperty(propertyName)));
  }
}
