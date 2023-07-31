class PlaceHold {
  void setString(String string, int flags) {
    if ((string == data.string)
        && ((flags & (~SWT.DRAW_TRANSPARENT)) == (data.drawFlags & (~SWT.DRAW_TRANSPARENT)))) {
      return;
    }
    byte[] buffer;
    int mnemonic;
    int length = string.length();
    int layout = data.layout;
    char[] text = new char[length];
    string.getChars(0, length, text, 0);
    if (((flags & SWT.DRAW_MNEMONIC) != 0) && ((mnemonic = fixMnemonic(text)) != (-1))) {
      char[] text1 = new char[mnemonic - 1];
      System.arraycopy(text, 0, text1, 0, text1.length);
      byte[] buffer1 = Converter.wcsToMbcs(null, text1, false);
      char[] text2 = new char[text.length - mnemonic];
      System.arraycopy(text, mnemonic - 1, text2, 0, text2.length);
      byte[] buffer2 = Converter.wcsToMbcs(null, text2, false);
      buffer = new byte[buffer1.length + buffer2.length];
      System.arraycopy(buffer1, 0, buffer, 0, buffer1.length);
      System.arraycopy(buffer2, 0, buffer, buffer1.length, buffer2.length);
      int attr_list = OS.pango_attr_list_new();
      int attr = OS.pango_attr_underline_new(PANGO_UNDERLINE_LOW);
      PangoAttribute attribute = new PangoAttribute();
      OS.memmove(attribute, attr, sizeof);
      attribute.start_index = buffer1.length;
      attribute.end_index = buffer1.length + 1;
      OS.memmove(attr, attribute, sizeof);
      OS.pango_attr_list_insert(attr_list, attr);
      OS.pango_layout_set_attributes(layout, attr_list);
      OS.pango_attr_list_unref(attr_list);
    } else {
      buffer = Converter.wcsToMbcs(null, text, false);
      OS.pango_layout_set_attributes(layout, 0);
    }
    OS.pango_layout_set_text(layout, buffer, buffer.length);
    OS.pango_layout_set_single_paragraph_mode(layout, (flags & SWT.DRAW_DELIMITER) == 0);
    OS.pango_layout_set_tabs(layout, (flags & SWT.DRAW_TAB) != 0 ? 0 : data.device.emptyTab);
    data.string = string;
    data.stringWidth = data.stringHeight = -1;
    data.drawFlags = flags;
  }
}
