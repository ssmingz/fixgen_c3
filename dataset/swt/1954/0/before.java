class PlaceHold {
  public void setVisible(boolean visible) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if ((style & SWT.POP_UP) == 0) {
      return;
    }
    if (visible) {
      PhPoint_t pt = new PhPoint_t();
      pt.x = ((short) (x));
      pt.y = ((short) (y));
      if (!hasLocation) {
        int ig = OS.PhInputGroup(0);
        PhCursorInfo_t info = new PhCursorInfo_t();
        OS.PhQueryCursor(((short) (ig)), info);
        pt.x = info.last_press_x;
        pt.y = info.last_press_y;
      }
      int ptr = OS.malloc(sizeof);
      OS.memmove(ptr, pt, sizeof);
      int[] args = new int[] {OS.Pt_ARG_POS, ptr, 0};
      OS.PtSetResources(handle, args.length / 3, args);
      OS.free(ptr);
      sendEvent(Show);
      OS.PtRealizeWidget(handle);
    } else {
      OS.PtUnrealizeWidget(handle);
    }
  }
}
