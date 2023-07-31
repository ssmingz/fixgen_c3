class PlaceHold {
  static String getCodePage(int xDisplay, int fontList) {
    int[] buffer = new int[1];
    if (!OS.XmFontListInitFontContext(buffer, fontList)) {
      return null;
    }
    int context = buffer[0];
    XFontStruct fontStruct = new XFontStruct();
    int fontListEntry;
    String codePage = null;
    while ((fontListEntry = OS.XmFontListNextEntry(context)) != 0) {
      int fontPtr = OS.XmFontListEntryGetFont(fontListEntry, buffer);
      if (buffer[0] == OS.XmFONT_IS_FONT) {
        OS.memmove(fontStruct, fontPtr, 20 * 4);
        int propPtr = fontStruct.properties;
        for (int i = 0; i < fontStruct.n_properties; i++) {
          int[] prop = new int[2];
          OS.memmove(prop, propPtr, 8);
          if (prop[0] == OS.XA_FONT) {
            int ptr = OS.XmGetAtomName(xDisplay, prop[1]);
            int length = OS.strlen(ptr);
            byte[] nameBuf = new byte[length];
            OS.memmove(nameBuf, ptr, length);
            String xlfd = new String(Converter.mbcsToWcs(null, nameBuf)).toLowerCase();
            int start = xlfd.lastIndexOf('-');
            if ((start != (-1)) && (start > 0)) {
              start = xlfd.lastIndexOf('-', start - 1);
              if (start != (-1)) {
                codePage = xlfd.substring(start + 1, xlfd.length());
                if (codePage.indexOf("iso") == 0) {
                  if (OS.IsLinux) {
                    codePage = "ISO-" + codePage.substring(3, codePage.length());
                  }
                }
              }
            }
            OS.XtFree(ptr);
            break;
          }
          propPtr += 8;
        }
      } else {
        int localePtr = OS.XLocaleOfFontSet(fontPtr);
        int length = OS.strlen(localePtr);
        byte[] locale = new byte[length + 1];
        OS.memmove(locale, localePtr, length);
        OS.setlocale(LC_CTYPE, locale);
        int codesetPtr = OS.nl_langinfo(CODESET);
        length = OS.strlen(codesetPtr);
        byte[] codeset = new byte[length];
        OS.memmove(codeset, codesetPtr, length);
        codePage = new String(codeset);
        OS.setlocale(LC_CTYPE, new byte[1]);
      }
    }
    OS.XmFontListFreeFontContext(context);
    return codePage;
  }
}
