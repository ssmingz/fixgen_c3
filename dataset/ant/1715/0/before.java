class PlaceHold {
  public void copyUserProperties(Project other) {
    PropertyHelper ph = PropertyHelper.getPropertyHelper(this);
    ph.copyUserProperties(other);
  }
}
