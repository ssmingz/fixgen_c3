class PlaceHold {
  public void scroll(int destX, int destY, int x, int y, int width, int height, boolean all) {
    checkWidget();
    forceResize();
    boolean isFocus = (caret != null) && caret.isFocusCaret();
    if (isFocus) {
      caret.killFocus();
    }
    RECT sourceRect = new RECT();
    OS.SetRect(sourceRect, x, y, x + width, y + height);
    RECT clientRect = new RECT();
    OS.GetClientRect(handle, clientRect);
    if (OS.IntersectRect(clientRect, sourceRect, clientRect)) {
      if (OS.IsWinCE) {
        OS.UpdateWindow(handle);
      } else {
        int flags = OS.RDW_UPDATENOW | OS.RDW_ALLCHILDREN;
        OS.RedrawWindow(handle, null, 0, flags);
      }
    }
    int deltaX = destX - x;
    int deltaY = destY - y;
    if (findImageControl() != null) {
      if (OS.IsWinCE) {
        OS.InvalidateRect(handle, sourceRect, true);
      } else {
        int flags = (OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE;
        if (all) {
          flags |= OS.RDW_ALLCHILDREN;
        }
        OS.RedrawWindow(handle, sourceRect, 0, flags);
      }
      OS.OffsetRect(sourceRect, deltaX, deltaY);
      if (OS.IsWinCE) {
        OS.InvalidateRect(handle, sourceRect, true);
      } else {
        int flags = (OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE;
        if (all) {
          flags |= OS.RDW_ALLCHILDREN;
        }
        OS.RedrawWindow(handle, sourceRect, 0, flags);
      }
    } else {
      int flags = OS.SW_INVALIDATE | OS.SW_ERASE;
      OS.ScrollWindowEx(handle, deltaX, deltaY, sourceRect, null, 0, null, flags);
    }
    if (all) {
      Control[] children = _getChildren();
      for (int i = 0; i < children.length; i++) {
        Control child = children[i];
        Rectangle rect = child.getBounds();
        if ((Math.min(x + width, rect.x + rect.width) >= Math.max(x, rect.x))
            && (Math.min(y + height, rect.y + rect.height) >= Math.max(y, rect.y))) {
          child.setLocation(rect.x + deltaX, rect.y + deltaY);
        }
      }
    }
    if (isFocus) {
      caret.setFocus();
    }
  }
}
