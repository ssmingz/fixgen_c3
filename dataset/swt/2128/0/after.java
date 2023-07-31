class PlaceHold {
  AccessibleListener[] getAccessibleListeners() {
    if (accessible == null) {
      return new AccessibleListener[0];
    }
    return accessible.getAccessibleListeners();
  }
}
