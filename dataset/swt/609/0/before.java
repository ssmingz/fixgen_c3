class PlaceHold {
  void setFontStyle(Font font) {
    if (txnObject == 0) {
      super.setFontStyle(font);
    } else {
      int family = OS.kTXNDefaultFontName;
      int fontStyle = OS.kTXNDefaultFontStyle;
      int size = OS.kTXNDefaultFontSize;
      if (font != null) {
        short[] id = new short[1];
        short[] s = new short[1];
        OS.FMGetFontFamilyInstanceFromFont(font.handle, id, s);
        family = id[0];
        fontStyle = s[0] | font.style;
        size = OS.X2Fix(font.size);
      }
      int[] attribs =
          new int[] {
            OS.kTXNQDFontSizeAttribute,
            OS.kTXNQDFontSizeAttributeSize,
            size,
            OS.kTXNQDFontStyleAttribute,
            OS.kTXNQDFontStyleAttributeSize,
            fontStyle,
            OS.kTXNQDFontFamilyIDAttribute,
            OS.kTXNQDFontFamilyIDAttributeSize,
            family
          };
      int ptr = OS.NewPtr(attribs.length * 4);
      OS.memcpy(ptr, attribs, attribs.length * 4);
      boolean readOnly = (style & SWT.READ_ONLY) != 0;
      int[] tag = new int[] {OS.kTXNIOPrivilegesTag};
      if (readOnly) {
        OS.TXNSetTXNObjectControls(txnObject, false, 1, tag, new int[] {0});
      }
      OS.TXNSetTypeAttributes(txnObject, attribs.length / 3, ptr, 0, 0);
      if (readOnly) {
        OS.TXNSetTXNObjectControls(txnObject, false, 1, tag, new int[] {1});
      }
      OS.DisposePtr(ptr);
    }
  }
}
