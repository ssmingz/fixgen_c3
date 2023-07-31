class PlaceHold {
  int traversalCode(int key, NSEvent theEvent) {
    int bits = super.traversalCode(key, theEvent);
    if ((style & SWT.READ_ONLY) != 0) {
      return bits;
    }
    if ((style & SWT.MULTI) != 0) {
      bits &= ~SWT.TRAVERSE_RETURN;
      if ((key == 48) && (theEvent != null)) {
        long modifiers = theEvent.modifierFlags();
        boolean next = (modifiers & OS.NSShiftKeyMask) == 0;
        if (next && ((modifiers & OS.NSControlKeyMask) == 0)) {
          bits &= ~(SWT.TRAVERSE_TAB_NEXT | SWT.TRAVERSE_TAB_PREVIOUS);
        }
      }
    }
    return bits;
  }
}
