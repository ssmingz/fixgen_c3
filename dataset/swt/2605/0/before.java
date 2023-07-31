class PlaceHold {
  public Point getCaretLocation() {
    checkWidget();
    GtkText gtktext = new GtkText();
    OS.memmove(gtktext, handle, sizeof);
    return new Point(gtktext.cursor_pos_x, gtktext.cursor_pos_y);
  }
}
