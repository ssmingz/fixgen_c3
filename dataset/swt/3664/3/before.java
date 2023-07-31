class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int sHandle = 0;
    try {
      sHandle = OS.CFStringCreateWithCharacters(removeMnemonics(string));
      OS.SetControlTitleWithCFString(handle, sHandle);
    } finally {
      if (sHandle != 0) {
        OS.CFRelease(sHandle);
      }
    }
  }
}
