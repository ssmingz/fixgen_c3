class PlaceHold {
  int setBounds(int x, int y, int width, int height, boolean move, boolean resize, boolean events) {
    checkWidget();
    if (OS.PtWidgetClass(shellHandle) != OS.PtWindow()) {
      int result = super.setBounds(x, y, width, height, move, resize, events);
      if ((result & RESIZED) != 0) {
        resizeBounds(width, height);
      }
      return result;
    }
    boolean isFocus = (caret != null) && caret.isFocusCaret();
    if (isFocus) {
      caret.killFocus();
    }
    if (resize) {
      int[] args = new int[] {OS.Pt_ARG_WINDOW_RENDER_FLAGS, 0, 0};
      OS.PtGetResources(shellHandle, args.length / 3, args);
      int flags = args[1];
      int[] left = new int[1];
      int[] top = new int[1];
      int[] right = new int[1];
      int[] bottom = new int[1];
      OS.PtFrameSize(flags, 0, left, top, right, bottom);
      width = Math.max((width - left[0]) - right[0], 0);
      height = Math.max((height - top[0]) - bottom[0], 0);
    }
    PhArea_t oldArea = new PhArea_t();
    OS.PtWidgetArea(shellHandle, oldArea);
    if (move && resize) {
      PhArea_t area = new PhArea_t();
      area.pos_x = ((short) (x));
      area.pos_y = ((short) (y));
      area.size_w = ((short) (width));
      area.size_h = ((short) (height));
      int ptr = OS.malloc(sizeof);
      OS.memmove(ptr, area, sizeof);
      OS.PtSetResource(shellHandle, Pt_ARG_AREA, ptr, 0);
      OS.free(ptr);
    } else if (move) {
      PhPoint_t pt = new PhPoint_t();
      pt.x = ((short) (x));
      pt.y = ((short) (y));
      int ptr = OS.malloc(sizeof);
      OS.memmove(ptr, pt, sizeof);
      OS.PtSetResource(shellHandle, Pt_ARG_POS, ptr, 0);
      OS.free(ptr);
    } else if (resize) {
      int[] args = new int[] {OS.Pt_ARG_WIDTH, width, 0, OS.Pt_ARG_HEIGHT, height, 0};
      OS.PtSetResources(shellHandle, args.length / 3, args);
    }
    if (!OS.PtWidgetIsRealized(shellHandle)) {
      PhArea_t newArea = new PhArea_t();
      OS.PtWidgetArea(shellHandle, newArea);
      boolean sameOrigin = (oldArea.pos_x == newArea.pos_x) && (oldArea.pos_y == newArea.pos_y);
      boolean sameExtent = (oldArea.size_w == newArea.size_w) && (oldArea.size_h == newArea.size_h);
      if ((!sameOrigin) & move) {
        sendEvent(Move);
      }
      if ((!sameExtent) & resize) {
        resizeBounds(newArea.size_w, newArea.size_h);
        sendEvent(Resize);
        if (layout != null) {
          layout(false);
        }
      }
    }
    if (isFocus) {
      caret.setFocus();
    }
    return 0;
  }
}
