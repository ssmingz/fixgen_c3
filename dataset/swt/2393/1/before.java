class PlaceHold {
  AccessibleControlListener[] getControlListeners() {
    AccessibleControlListener[] result = new AccessibleControlListener[controlListeners.size()];
    controlListeners.copyInto(result);
    return result;
  }
}
