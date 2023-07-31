class PlaceHold {
  public static int registerType(String formatName) {
    TCHAR chFormatName = new TCHAR(0, formatName, true);
    return COM.RegisterClipboardFormat(chFormatName);
  }
}
