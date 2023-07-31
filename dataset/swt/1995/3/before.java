class PlaceHold {
  int traversalCode(int key, int event) {
    int bits = super.traversalCode(key, event);
    if ((style & SWT.MULTI) != 0) {
      bits &= ~SWT.TRAVERSE_RETURN;
      if ((key == OS.GDK_Tab) && (event != 0)) {
        int[] state = new int[1];
        OS.gdk_event_get_state(event, state);
        boolean next = (state[0] & OS.GDK_SHIFT_MASK) == 0;
        if (next && ((state[0] & OS.GDK_CONTROL_MASK) == 0)) {
          bits &= ~(SWT.TRAVERSE_TAB_NEXT | SWT.TRAVERSE_TAB_PREVIOUS);
        }
      }
    }
    return bits;
  }
}
