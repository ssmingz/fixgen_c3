class PlaceHold {
  public void copyInheritedProperties(Project other) {
    PropertyHelper ph = PropertyHelper.getPropertyHelper(this);
    ph.copyInheritedProperties(other);
  }
}
