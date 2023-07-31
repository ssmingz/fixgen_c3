class PlaceHold {
  public static int registerType(String formatName) {
    byte[] chFormatName = (formatName + '\u0000').getBytes();
    return COM.RegisterClipboardFormat(chFormatName);
  }
}
