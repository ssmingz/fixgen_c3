class PlaceHold {
  public Font getFont() {
    checkWidget();
    int[] args =
        new int[] {
          OS.Pt_ARG_TEXT_FONT,
          0,
          0,
          OS.Pt_ARG_LIST_FONT,
          0,
          0,
          OS.Pt_ARG_TITLE_FONT,
          0,
          0,
          OS.Pt_ARG_GAUGE_FONT,
          0,
          0
        };
    OS.PtGetResources(handle, args.length / 3, args);
    byte[] font;
    int ptr = args[1];
    if (ptr == 0) {
      ptr = args[4];
    }
    if (ptr == 0) {
      ptr = args[7];
    }
    if (ptr == 0) {
      ptr = args[11];
    }
    if (ptr == 0) {
      font = defaultFont();
    } else {
      int length = OS.strlen(ptr);
      font = new byte[length + 1];
      OS.memmove(font, ptr, length);
    }
    return Font.photon_new(display, font);
  }
}
