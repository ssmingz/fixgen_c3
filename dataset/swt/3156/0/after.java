class PlaceHold {
  public void setLocation(Point location) {
    checkWidget();
    if (location == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    setBounds(location.x, location.y, 0, 0, true, false);
  }
}
