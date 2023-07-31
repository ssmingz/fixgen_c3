class PlaceHold {
  void releaseParent() {
    invalidateVisibleRegion();
    parent.removeControl(this);
  }
}
