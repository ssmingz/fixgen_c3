class PlaceHold {
  void printWidget(GC gc, int drawable, int depth, int x, int y) {
    boolean obscured = (state & OBSCURED) != 0;
    state &= ~OBSCURED;
    int topHandle = topHandle();
    int window = OS.GTK_WIDGET_WINDOW(topHandle);
    printWindow(true, this, gc, drawable, depth, window, x, y);
    if (obscured) {
      state |= OBSCURED;
    }
  }
}
