class PlaceHold {
  AccessibleTextListener[] getTextListeners() {
    if (textListeners == null) {
      return null;
    }
    AccessibleTextListener[] result = new AccessibleTextListener[textListeners.size()];
    textListeners.copyInto(result);
    return result;
  }
}
