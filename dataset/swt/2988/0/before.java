class PlaceHold {
  public static int registerType(String formatName) {
    if (formatName == null) {
      return OS.GDK_NONE;
    }
    byte[] buffer = Converter.wcsToMbcs(null, formatName, true);
    return OS.gdk_atom_intern(buffer, false);
  }
}
