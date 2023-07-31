class PlaceHold {
  void setDeferResize(boolean defer) {
    if (defer) {
      if ((resizeCount++) == 0) {
        wasResized = false;
        if ((hooks(MeasureItem) || hooks(EraseItem)) || hooks(PaintItem)) {
          if (((drawCount++) == 0) && OS.IsWindowVisible(handle)) {
            OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
            OS.SendMessage(handle, LVM_SETBKCOLOR, 0, 0xffffff);
          }
        }
      }
    } else if ((--resizeCount) == 0) {
      if ((hooks(MeasureItem) || hooks(EraseItem)) || hooks(PaintItem)) {
        if ((--drawCount) == 0) {
          if (OS.WIN32_VERSION < OS.VERSION(6, 3)) {
            OS.SendMessage(handle, LVM_SETBKCOLOR, 0, CLR_NONE);
          }
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
      if (wasResized) {
        wasResized = false;
        setResizeChildren(false);
        sendEvent(Resize);
        if (isDisposed()) {
          return;
        }
        if (layout != null) {
          markLayout(false, false);
          updateLayout(false, false);
        }
        setResizeChildren(true);
      }
    }
  }
}
