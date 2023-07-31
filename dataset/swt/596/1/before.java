class PlaceHold {
  public void setCursor(Cursor cursor) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    int type = OS.Ph_CURSOR_INHERIT;
    int bitmap = 0;
    if (cursor != null) {
      type = cursor.type;
      bitmap = cursor.bitmap;
    }
    int[] args = new int[] {OS.Pt_ARG_CURSOR_TYPE, type, 0, OS.Pt_ARG_BITMAP_CURSOR, bitmap, 0};
    OS.PtSetResources(handle, args.length / 3, args);
    if (type == OS.Ph_CURSOR_BITMAP) {
      type &= ~OS.Ph_CURSOR_NO_INHERIT;
      args = new int[] {OS.Pt_ARG_CURSOR_TYPE, type, 0};
      OS.PtSetResources(handle, args.length / 3, args);
    }
  }
}
