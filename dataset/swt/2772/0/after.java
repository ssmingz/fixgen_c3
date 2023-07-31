class PlaceHold {
  void setScrollWidth(int width) {
    if (width != ((int) (OS.SendMessage(handle, LVM_GETCOLUMNWIDTH, 0, 0)))) {
      boolean redraw = false;
      if (hooks(MeasureItem)) {
        redraw = getDrawing() && OS.IsWindowVisible(handle);
      }
      if (redraw) {
        OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
      }
      OS.SendMessage(handle, LVM_SETCOLUMNWIDTH, 0, width);
      if (redraw) {
        OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
        if (OS.IsWinCE) {
          long hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
          if (hwndHeader != 0) {
            OS.InvalidateRect(hwndHeader, null, true);
          }
          OS.InvalidateRect(handle, null, true);
        } else {
          int flags = ((OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE) | OS.RDW_ALLCHILDREN;
          OS.RedrawWindow(handle, null, 0, flags);
        }
      }
    }
  }
}
