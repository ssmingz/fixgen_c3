class PlaceHold {
  AccessibleControlListener[] getControlListeners() {
    if (controlListeners == null) {
      return null;
    }
    AccessibleControlListener[] result = new AccessibleControlListener[controlListeners.size()];
    controlListeners.copyInto(result);
    return result;
  }
}
