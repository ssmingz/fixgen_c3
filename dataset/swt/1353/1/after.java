class PlaceHold {
  public Point getDPI() {
    checkDevice();
    byte[] buffer = Converter.wcsToMbcs(null, "default-printer-resolution", true);
    int pool = OS.XpGetOneAttribute(xDisplay, printContext, XPDocAttr, buffer);
    int length = OS.strlen(pool);
    buffer = new byte[length];
    OS.memmove(buffer, pool, length);
    OS.XtFree(pool);
    String resolution = new String(buffer, 0, buffer.length);
    int res = 300;
    if (resolution.length() == 0) {
      buffer = Converter.wcsToMbcs(null, "printer-resolutions-supported", true);
      pool = OS.XpGetOneAttribute(xDisplay, printContext, XPPrinterAttr, buffer);
      length = OS.strlen(pool);
      buffer = new byte[length];
      OS.memmove(buffer, pool, length);
      OS.XtFree(pool);
      int n = 0;
      while ((!Compatibility.isWhitespace(((char) (buffer[n])))) && (n < buffer.length)) {
        n++;
      }
      resolution = new String(buffer, 0, n);
    }
    if (resolution.length() != 0) {
      try {
        res = Integer.parseInt(resolution);
      } catch (NumberFormatException ex) {
      }
    }
    return new Point(res, res);
  }
}
