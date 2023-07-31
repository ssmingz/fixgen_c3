class PlaceHold {
  int traversalCode(int key, XKeyEvent xEvent) {
    int bits = super.traversalCode(key, xEvent);
    if ((style & SWT.READ_ONLY) != 0) {
      return bits;
    }
    if ((style & SWT.MULTI) != 0) {
      bits &= ~SWT.TRAVERSE_RETURN;
      if ((key == OS.XK_Tab) && (xEvent != null)) {
        boolean next = (xEvent.state & OS.ShiftMask) == 0;
        if (next && ((xEvent.state & OS.ControlMask) == 0)) {
          bits &= ~(SWT.TRAVERSE_TAB_NEXT | SWT.TRAVERSE_TAB_PREVIOUS);
        }
      }
    }
    return bits;
  }
}
