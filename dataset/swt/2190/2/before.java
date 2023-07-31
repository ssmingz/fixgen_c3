class PlaceHold {
  int okFunc(int widget, int callData) {
    int fontName = OS.gtk_font_selection_dialog_get_font_name(callData);
    int length = OS.strlen(fontName);
    byte[] buffer = new byte[length];
    OS.memmove(buffer, fontName, length);
    int fontDesc = OS.pango_font_description_from_string(buffer);
    Display display = (parent != null) ? parent.getDisplay() : Display.getCurrent();
    Font font = Font.gtk_new(display, fontDesc);
    fontData = font.getFontData()[0];
    OS.pango_font_description_free(fontDesc);
    OS.gtk_widget_destroy(callData);
    return 0;
  }
}
