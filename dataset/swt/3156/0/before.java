class PlaceHold {
  public void setLocation(Point location) {
    if (location == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    setLocation(location.x, location.y);
  }
}
