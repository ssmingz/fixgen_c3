class PlaceHold {
  int focusHandle() {
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      if (((style & SWT.READ_ONLY) != 0) && (buttonHandle != 0)) {
        return buttonHandle;
      }
    }
    if (entryHandle != 0) {
      return entryHandle;
    }
    return super.focusHandle();
  }
}
