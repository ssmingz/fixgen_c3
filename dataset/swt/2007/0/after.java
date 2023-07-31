class PlaceHold {
  NSAttributedString createString(String string, int flags, boolean draw) {
    NSMutableDictionary dict =
        ((NSMutableDictionary) (new NSMutableDictionary().alloc())).initWithCapacity(5);
    Font font = data.font;
    dict.setObject(font.handle, NSFontAttributeName);
    font.addTraits(dict);
    if (draw) {
      Pattern pattern = data.foregroundPattern;
      if (pattern != null) {
        if (pattern.color != null) {
          dict.setObject(pattern.color, NSForegroundColorAttributeName);
        }
      } else {
        NSColor fg = data.fg;
        if (fg == null) {
          double[] color = data.foreground;
          fg =
              data.fg =
                  NSColor.colorWithDeviceRed(color[0], color[1], color[2], data.alpha / 255.0F);
          fg.retain();
        }
        dict.setObject(fg, NSForegroundColorAttributeName);
      }
    }
    if ((flags & SWT.DRAW_TAB) == 0) {
      dict.setObject(paragraphStyle, NSParagraphStyleAttributeName);
    }
    int length = string.length();
    char[] chars = new char[length];
    string.getChars(0, length, chars, 0);
    if (((flags & SWT.DRAW_MNEMONIC) != 0) || ((flags & SWT.DRAW_DELIMITER) == 0)) {
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
              if ((flags & SWT.DRAW_DELIMITER) == 0) {
                if (((c == '\r') && (i != chars.length)) && (chars[i] == '\n')) {
                  i++;
                }
                j--;
              }
              break;
            }
        }
      }
      length = j;
    }
    NSString str = ((NSString) (new NSString().alloc())).initWithCharacters(chars, length);
    NSAttributedString attribStr =
        ((NSAttributedString) (new NSAttributedString().alloc())).initWithString(str, dict);
    dict.release();
    str.release();
    return attribStr;
  }
}
