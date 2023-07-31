class PlaceHold {
  private void onPaint(Event e) {
    if ((state == STATE_RUNNING) || (state == STATE_INPLACEACTIVE)) {
      SIZE size = getExtent();
      Rectangle area = getClientArea();
      RECT rect = new RECT();
      if (getProgramID().startsWith("Excel.Sheet")) {
        rect.left = area.x;
        rect.right = area.x + ((area.height * size.cx) / size.cy);
        rect.top = area.y;
        rect.bottom = area.y + area.height;
      } else {
        rect.left = area.x;
        rect.right = area.x + size.cx;
        rect.top = area.y;
        rect.bottom = area.y + size.cy;
      }
      int pArea = OS.GlobalAlloc(COM.GMEM_FIXED | COM.GMEM_ZEROINIT, sizeof);
      OS.MoveMemory(pArea, rect, sizeof);
      COM.OleDraw(objIUnknown.getAddress(), aspect, e.gc.handle, pArea);
      OS.GlobalFree(pArea);
    }
  }
}
