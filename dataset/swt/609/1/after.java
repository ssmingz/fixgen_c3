class PlaceHold {
  int setString(String string, int flags) {
    if (data.layout == 0) {
      createLayout();
    }
    if ((string == data.string)
        && ((flags & (~SWT.DRAW_TRANSPARENT)) == (data.drawFlags & (~SWT.DRAW_TRANSPARENT)))) {
      return data.stringLength;
    }
    int layout = data.layout;
    int length = string.length();
    char[] chars = new char[length];
    string.getChars(0, length, chars, 0);
    int breakCount = 0;
    int[] breaks = null;
    if ((flags & (SWT.DRAW_MNEMONIC | SWT.DRAW_DELIMITER)) != 0) {
      int i = 0;
      int j = 0;
      while (i < chars.length) {
        char c = chars[j++] = chars[i++];
        switch (c) {
          case '&':
            {
              if ((flags & SWT.DRAW_MNEMONIC) != 0) {
                if (i == chars.length) {
                  continue;
                }
                if (chars[i] == '&') {
                  i++;
                  continue;
                }
                j--;
              }
              break;
            }
          case '\r':
          case '\n':
            {
              if ((flags & SWT.DRAW_DELIMITER) != 0) {
                if (((c == '\r') && (i != chars.length)) && (chars[i] == '\n')) {
                  i++;
                }
                j--;
                if (breaks == null) {
                  breaks = new int[4];
                } else if (breakCount == breaks.length) {
                  int[] newBreaks = new int[breaks.length + 4];
                  System.arraycopy(breaks, 0, newBreaks, 0, breaks.length);
                  breaks = newBreaks;
                }
                breaks[breakCount++] = j;
              }
              break;
            }
        }
      }
      length = j;
    }
    if ((flags & SWT.DRAW_TAB) != 0) {
      if (data.tabs == 0) {
        createTabs();
      }
      OS.ATSUSetTabArray(layout, data.tabs, TAB_COUNT);
    } else {
      OS.ATSUSetTabArray(layout, 0, 0);
    }
    int ptr = OS.NewPtr(length * 2);
    OS.memmove(ptr, chars, length * 2);
    OS.ATSUSetTextPointerLocation(layout, ptr, 0, length, length);
    if (((flags & SWT.DRAW_DELIMITER) != 0) && (breaks != null)) {
      for (int i = 0; i < breakCount; i++) {
        OS.ATSUSetSoftLineBreak(layout, breaks[i]);
      }
    }
    Font font = data.font;
    int atsuiStyle = (font.atsuiStyle != 0) ? font.atsuiStyle : data.atsuiStyle;
    OS.ATSUSetRunStyle(layout, atsuiStyle, 0, length);
    OS.ATSUSetTransientFontMatching(layout, true);
    if (data.stringPtr != 0) {
      OS.DisposePtr(data.stringPtr);
    }
    data.stringPtr = ptr;
    data.string = string;
    data.stringLength = length;
    data.stringWidth = data.stringHeight = -1;
    data.drawFlags = flags;
    return length;
  }
}
