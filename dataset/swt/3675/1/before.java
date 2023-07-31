class PlaceHold {
  public Point getSelection() {
    checkWidget();
    GtkEditable widget = new GtkEditable();
    OS.memmove(widget, handle, sizeof);
    return new Point(widget.selection_start_pos, widget.selection_end_pos);
  }
}
