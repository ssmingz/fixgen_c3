class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.ARROW) != 0) {
      return;
    }
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    super.setText(string);
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
    ControlTabInfoRecV1 tab = new ControlTabInfoRecV1();
    tab.version = ((short) (OS.kControlTabInfoVersionOne));
    tab.iconSuiteID = 0;
    tab.name = ptr;
    OS.SetControlData(parent.handle, index + 1, kControlTabInfoTag, sizeof, tab);
    OS.CFRelease(ptr);
  }
}
