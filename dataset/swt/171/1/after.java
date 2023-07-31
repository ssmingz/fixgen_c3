class PlaceHold {
  long rendererGetSizeProc(
      long cell,
      long widget,
      long cell_area,
      long x_offset,
      long y_offset,
      long width,
      long height) {
    long g_class = OS.g_type_class_peek_parent(OS.G_OBJECT_GET_CLASS(cell));
    GtkCellRendererClass klass = new GtkCellRendererClass();
    OS.memmove(klass, g_class);
    OS.call_get_size(klass.get_size, cell, handle, cell_area, x_offset, y_offset, width, height);
    sendMeasureEvent(cell, width, height);
    return 0;
  }
}
