class PlaceHold {
  public Point getCaretLocation() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      return new Point(0, 0);
    }
    int index = OS.gtk_editable_get_position(entryHandle);
    if (OS.GTK_VERSION >= OS.VERSION(2, 6, 0)) {
      index = OS.gtk_entry_text_index_to_layout_index(entryHandle, index);
    }
    int[] offset_x = new int[1];
    int[] offset_y = new int[1];
    OS.gtk_entry_get_layout_offsets(entryHandle, offset_x, offset_y);
    long layout = OS.gtk_entry_get_layout(entryHandle);
    PangoRectangle pos = new PangoRectangle();
    OS.pango_layout_index_to_pos(layout, index, pos);
    int x = (offset_x[0] + OS.PANGO_PIXELS(pos.x)) - getBorderWidth();
    int y = offset_y[0] + OS.PANGO_PIXELS(pos.y);
    return new Point(x, y);
  }
}
