class PlaceHold {
  int traversalCode(int key, NSEvent theEvent) {
    if (offsets.length == 0) {
      return 0;
    }
    int bits = super.traversalCode(key, theEvent);
    if ((key == 48) && (theEvent != null)) {
      long modifierFlags = theEvent.modifierFlags();
      boolean next = (modifierFlags & OS.NSShiftKeyMask) == 0;
      if (next && (focusIndex < (offsets.length - 1))) {
        return bits & (~SWT.TRAVERSE_TAB_NEXT);
      }
      if ((!next) && (focusIndex > 0)) {
        return bits & (~SWT.TRAVERSE_TAB_PREVIOUS);
      }
    }
    return bits;
  }
}
