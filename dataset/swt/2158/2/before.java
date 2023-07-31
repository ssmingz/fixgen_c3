class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    super.setText(string);
    if (OS.HIVIEW) {
      char[] buffer = new char[text.length()];
      text.getChars(0, buffer.length, buffer, 0);
      int length = fixMnemonic(buffer);
      int ptr = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, length);
      if (ptr == 0) {
        error(ERROR_CANNOT_SET_TEXT);
      }
      OS.SetControlData(labelHandle, 0, kControlStaticTextCFStringTag, 4, new int[] {ptr});
      OS.CFRelease(ptr);
      redrawWidget(labelHandle, false);
    } else {
      updateText(true);
    }
  }
}
