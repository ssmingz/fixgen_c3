class PlaceHold {
  void addAccelerator() {
    if (accelerator == 0) {
      return;
    }
    if (OS.IsSunOS) {
      return;
    }
    String ctrl;
    String alt;
    String shift;
    ctrl = alt = shift = "";
    if ((accelerator & SWT.ALT) != 0) {
      alt = "Meta ";
    }
    if ((accelerator & SWT.SHIFT) != 0) {
      shift = "Shift ";
    }
    if ((accelerator & SWT.CONTROL) != 0) {
      ctrl = "Ctrl ";
    }
    int keysym = accelerator & SWT.KEY_MASK;
    int newKey = Display.untranslateKey(keysym);
    if (newKey != 0) {
      keysym = newKey;
    } else {
      keysym = wcsToMbcs(((char) (keysym)));
    }
    String key = (((ctrl + alt) + shift) + "<Key>") + keysymName(keysym);
    String allKeys = (key + ",Lock ") + key;
    String numLock = Display.numLock;
    if (numLock != null) {
      allKeys += (((((("," + numLock) + " ") + key) + ",Lock ") + numLock) + " ") + key;
    }
    byte[] buffer = Converter.wcsToMbcs(null, allKeys, true);
    int ptr = OS.XtMalloc(buffer.length);
    if (ptr != 0) {
      OS.memmove(ptr, buffer, buffer.length);
    }
    int[] argList = new int[] {OS.XmNaccelerator, ptr};
    OS.XtSetValues(handle, argList, argList.length / 2);
    if (ptr != 0) {
      OS.XtFree(ptr);
    }
  }
}
