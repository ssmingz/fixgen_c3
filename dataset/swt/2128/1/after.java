class PlaceHold {
  AccessibleControlListener[] getControlListeners() {
    if (accessible == null) {
      return new AccessibleControlListener[0];
    }
    return accessible.getControlListeners();
  }
}
