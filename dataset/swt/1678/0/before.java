class PlaceHold {
  void updateAccelerator(int accelGroup, boolean add) {
    if (accelerator == 0) {
      return;
    }
    int mask = 0;
    if ((accelerator & SWT.ALT) != 0) {
      mask |= OS.GDK_MOD1_MASK;
    }
    if ((accelerator & SWT.SHIFT) != 0) {
      mask |= OS.GDK_SHIFT_MASK;
    }
    if ((accelerator & SWT.CONTROL) != 0) {
      mask |= OS.GDK_CONTROL_MASK;
    }
    int modifiers = (SWT.ALT | SWT.SHIFT) | SWT.CONTROL;
    int keysym = accelerator & (~modifiers);
    int newKey = Display.untranslateKey(keysym);
    if (newKey != 0) {
      keysym = newKey;
    } else {
      switch (keysym) {
        case '\r':
          keysym = OS.GDK_Return;
          break;
        default:
          keysym = wcsToMbcs(((char) (keysym)));
      }
    }
    if (add) {
      OS.gtk_widget_add_accelerator(handle, activate, accelGroup, keysym, mask, GTK_ACCEL_VISIBLE);
    } else {
      OS.gtk_widget_remove_accelerator(handle, accelGroup, keysym, mask);
    }
  }
}
