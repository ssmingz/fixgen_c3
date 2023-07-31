class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    isImage = false;
    text = string;
    char[] buffer = new char[text.length()];
    text.getChars(0, buffer.length, buffer, 0);
    int i = 0;
    int j = 0;
    while (i < buffer.length) {
      if ((buffer[j++] = buffer[i++]) == Mnemonic) {
        if (i == buffer.length) {
          continue;
        }
        if (buffer[i] == Mnemonic) {
          i++;
          continue;
        }
        j--;
      }
    }
    int ptr = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, j);
    if (ptr == 0) {
      error(ERROR_CANNOT_SET_TEXT);
    }
    OS.SetControlData(handle, 0, kControlStaticTextCFStringTag, 4, new int[] {ptr});
    OS.CFRelease(ptr);
    redraw();
  }
}
