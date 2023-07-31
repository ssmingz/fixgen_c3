class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.ARROW) != 0) {
      return;
    }
    text = string;
    char[] buffer = new char[text.length()];
    text.getChars(0, buffer.length, buffer, 0);
    int length = fixMnemonic(buffer);
    int ptr = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, length);
    if (ptr == 0) {
      error(ERROR_CANNOT_SET_TEXT);
    }
    OS.SetControlTitleWithCFString(handle, ptr);
    OS.CFRelease(ptr);
  }
}
