class PlaceHold {
  AccessibleTextListener[] getTextListeners() {
    AccessibleTextListener[] result = new AccessibleTextListener[textListeners.size()];
    textListeners.copyInto(result);
    return result;
  }
}
