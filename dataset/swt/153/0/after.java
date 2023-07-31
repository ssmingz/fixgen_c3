class PlaceHold {
  public void setAccelerator(int accelerator) {
    checkWidget();
    this.accelerator = accelerator;
    if (OS.IsSunOS) {
      return;
    }
    int ptr = 0;
    if (accelerator != 0) {
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
      if ((accelerator & SWT.CTRL) != 0) {
        ctrl = "Ctrl ";
      }
      int keysym = accelerator & (~((SWT.ALT | SWT.SHIFT) | SWT.CTRL));
      int newKey = Display.untranslateKey(keysym);
      if (newKey != 0) {
        keysym = newKey;
      } else {
        keysym = wcsToMbcs(((char) (keysym)));
      }
      String key = (((ctrl + alt) + shift) + "<Key>") + keysymName(keysym);
      String allKeys = (((((key + ",Lock ") + key) + ",Mod2 ") + key) + ",Lock Mod2 ") + key;
      byte[] buffer = Converter.wcsToMbcs(null, allKeys, true);
      ptr = OS.XtMalloc(buffer.length);
      if (ptr != 0) {
        OS.memmove(ptr, buffer, buffer.length);
      }
    }
    int[] argList = new int[] {OS.XmNaccelerator, ptr};
    OS.XtSetValues(handle, argList, argList.length / 2);
    if (ptr != 0) {
      OS.XtFree(ptr);
    }
  }
}
