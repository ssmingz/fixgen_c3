class PlaceHold {
  AccessibleTextListener[] getTextListeners() {
    if (accessible == null) {
      return new AccessibleTextListener[0];
    }
    return accessible.getTextListeners();
  }
}
