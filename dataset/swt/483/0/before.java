class PlaceHold {
  AccessibleListener[] getAccessibleListeners() {
    AccessibleListener[] result = new AccessibleListener[accessibleListeners.size()];
    accessibleListeners.copyInto(result);
    return result;
  }
}
