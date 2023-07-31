class PlaceHold {
  public String getText() {
    checkWidget();
    GtkCombo combo = new GtkCombo();
    OS.memmove(combo, handle, sizeof);
    int address = OS.gtk_entry_get_text(combo.entry);
    int length = OS.strlen(address);
    byte[] buffer1 = new byte[length];
    OS.memmove(buffer1, address, length);
    char[] buffer2 = Converter.mbcsToWcs(null, buffer1);
    return new String(buffer2, 0, buffer2.length);
  }
}
