class PlaceHold {
  AccessibleListener[] getAccessibleListeners() {
    if (accessibleListeners == null) {
      return null;
    }
    AccessibleListener[] result = new AccessibleListener[accessibleListeners.size()];
    accessibleListeners.copyInto(result);
    return result;
  }
}
