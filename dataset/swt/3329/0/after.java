class PlaceHold {
  public int getCharWidth(char ch) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int fontList = data.font.handle;
    byte[] charBuffer = Converter.wcsToMbcs(getCodePage(), new char[] {ch}, false);
    int val = charBuffer[0] & 0xff;
    int[] buffer = new int[1];
    if (!OS.XmFontListInitFontContext(buffer, fontList)) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int context = buffer[0];
    XFontStruct fontStruct = new XFontStruct();
    XCharStruct charStruct = new XCharStruct();
    int fontListEntry;
    int[] fontStructPtr = new int[1];
    int[] fontNamePtr = new int[1];
    while ((fontListEntry = OS.XmFontListNextEntry(context)) != 0) {
      int fontPtr = OS.XmFontListEntryGetFont(fontListEntry, buffer);
      if (buffer[0] == 0) {
        OS.memmove(fontStruct, fontPtr, sizeof);
        if ((fontStruct.min_byte1 == 0) && (fontStruct.max_byte1 == 0)) {
          if ((fontStruct.min_char_or_byte2 <= val) && (val <= fontStruct.max_char_or_byte2)) {
            int charWidth = 0;
            int lBearing = 0;
            int rBearing = 0;
            int perCharPtr = fontStruct.per_char;
            if (perCharPtr == 0) {
              charWidth = fontStruct.max_bounds_width;
              lBearing = fontStruct.min_bounds_lbearing;
              rBearing = fontStruct.max_bounds_rbearing;
            } else {
              OS.memmove(
                  charStruct,
                  perCharPtr + ((val - fontStruct.min_char_or_byte2) * XCharStruct.sizeof),
                  sizeof);
              charWidth = charStruct.width;
              lBearing = charStruct.lbearing;
              rBearing = charStruct.rbearing;
            }
            if (charWidth != 0) {
              OS.XmFontListFreeFontContext(context);
              return rBearing - lBearing;
            }
          }
        } else {
          int charsPerRow = (fontStruct.max_char_or_byte2 - fontStruct.min_char_or_byte2) + 1;
          int row = 0;
          if (charBuffer.length > 1) {
            row = charBuffer[1] - fontStruct.min_byte1;
          }
          int col = charBuffer[0] - fontStruct.min_char_or_byte2;
          if ((row <= fontStruct.max_byte1) && (col <= fontStruct.max_char_or_byte2)) {
            int charWidth = 0;
            int lBearing = 0;
            int rBearing = 0;
            int perCharPtr = fontStruct.per_char;
            if (perCharPtr == 0) {
              charWidth = fontStruct.max_bounds_width;
              lBearing = fontStruct.min_bounds_lbearing;
              rBearing = fontStruct.max_bounds_rbearing;
            } else {
              int offset = (row * charsPerRow) + col;
              OS.memmove(charStruct, perCharPtr + (offset * XCharStruct.sizeof), sizeof);
              charWidth = charStruct.width;
              lBearing = charStruct.lbearing;
              rBearing = charStruct.rbearing;
            }
            if (charWidth != 0) {
              OS.XmFontListFreeFontContext(context);
              return rBearing - lBearing;
            }
          }
        }
      } else {
        int nFonts = OS.XFontsOfFontSet(fontPtr, fontStructPtr, fontNamePtr);
        int[] fontStructs = new int[nFonts];
        OS.memmove(fontStructs, fontStructPtr[0], nFonts * 4);
        for (int i = 0; i < nFonts; i++) {
          OS.memmove(fontStruct, fontStructs[i], sizeof);
          if ((fontStruct.min_byte1 == 0) && (fontStruct.max_byte1 == 0)) {
            if ((fontStruct.min_char_or_byte2 <= val) && (val <= fontStruct.max_char_or_byte2)) {
              int charWidth = 0;
              int lBearing = 0;
              int rBearing = 0;
              int perCharPtr = fontStruct.per_char;
              if (perCharPtr == 0) {
                charWidth = fontStruct.max_bounds_width;
                lBearing = fontStruct.min_bounds_lbearing;
                rBearing = fontStruct.max_bounds_rbearing;
              } else {
                OS.memmove(
                    charStruct,
                    perCharPtr + (val - (fontStruct.min_char_or_byte2 * XCharStruct.sizeof)),
                    sizeof);
                charWidth = charStruct.width;
                lBearing = charStruct.lbearing;
                rBearing = charStruct.rbearing;
              }
              if (charWidth != 0) {
                OS.XmFontListFreeFontContext(context);
                return rBearing - lBearing;
              }
            }
          } else {
            int charsPerRow = (fontStruct.max_char_or_byte2 - fontStruct.min_char_or_byte2) + 1;
            int row = 0;
            if (charBuffer.length > 1) {
              row = charBuffer[1] - fontStruct.min_byte1;
            }
            int col = charBuffer[0] - fontStruct.min_char_or_byte2;
            if ((row <= fontStruct.max_byte1) && (col <= fontStruct.max_char_or_byte2)) {
              int charWidth = 0;
              int lBearing = 0;
              int rBearing = 0;
              int perCharPtr = fontStruct.per_char;
              if (perCharPtr == 0) {
                charWidth = fontStruct.max_bounds_width;
                lBearing = fontStruct.min_bounds_lbearing;
                rBearing = fontStruct.max_bounds_rbearing;
              } else {
                int offset = (row * charsPerRow) + col;
                OS.memmove(charStruct, perCharPtr + (offset * XCharStruct.sizeof), sizeof);
                charWidth = charStruct.width;
                lBearing = charStruct.lbearing;
                rBearing = charStruct.rbearing;
              }
              if (charWidth != 0) {
                OS.XmFontListFreeFontContext(context);
                return rBearing - lBearing;
              }
            }
          }
        }
      }
    }
    OS.XmFontListFreeFontContext(context);
    return 0;
  }
}
