class PlaceHold {
  LRESULT sendMouseDownEvent(int type, int button, int msg, int wParam, int lParam) {
    LVHITTESTINFO pinfo = new LVHITTESTINFO();
    pinfo.x = ((short) (lParam & 0xffff));
    pinfo.y = ((short) (lParam >> 16));
    OS.SendMessage(handle, LVM_HITTEST, 0, pinfo);
    Display display = this.display;
    display.captureChanged = false;
    if (!sendMouseEvent(type, button, handle, msg, wParam, lParam)) {
      if ((!display.captureChanged) && (!isDisposed())) {
        if (OS.GetCapture() != handle) {
          OS.SetCapture(handle);
        }
      }
      return LRESULT.ZERO;
    }
    OS.SetFocus(handle);
    if ((((style & SWT.SINGLE) != 0) || hooks(MouseDown)) || hooks(MouseUp)) {
      if (pinfo.iItem == (-1)) {
        if ((!display.captureChanged) && (!isDisposed())) {
          if (OS.GetCapture() != handle) {
            OS.SetCapture(handle);
          }
        }
        return LRESULT.ZERO;
      }
    }
    boolean forceSelect = false;
    int count = OS.SendMessage(handle, LVM_GETSELECTEDCOUNT, 0, 0);
    if ((count == 1) && (pinfo.iItem != (-1))) {
      LVITEM lvItem = new LVITEM();
      lvItem.mask = OS.LVIF_STATE;
      lvItem.stateMask = OS.LVIS_SELECTED;
      lvItem.iItem = pinfo.iItem;
      OS.SendMessage(handle, LVM_GETITEM, 0, lvItem);
      if ((lvItem.state & OS.LVIS_SELECTED) != 0) {
        forceSelect = true;
      }
    }
    boolean dragDetect = ((state & DRAG_DETECT) != 0) && hooks(DragDetect);
    if (!dragDetect) {
      int flags = OS.LVHT_ONITEMICON | OS.LVHT_ONITEMLABEL;
      dragDetect = (pinfo.iItem == (-1)) || ((pinfo.flags & flags) == 0);
    }
    if (!dragDetect) {
      display.runDragDrop = false;
    }
    int code = callWindowProc(handle, msg, wParam, lParam, forceSelect);
    if (!dragDetect) {
      display.runDragDrop = true;
    }
    if (dragStarted || (!dragDetect)) {
      if ((!display.captureChanged) && (!isDisposed())) {
        if (OS.GetCapture() != handle) {
          OS.SetCapture(handle);
        }
      }
    } else {
      int flags = OS.LVHT_ONITEMLABEL | OS.LVHT_ONITEMICON;
      boolean fakeMouseUp = (pinfo.flags & flags) != 0;
      if ((!fakeMouseUp) && ((style & SWT.MULTI) != 0)) {
        fakeMouseUp = (pinfo.flags & OS.LVHT_ONITEMSTATEICON) == 0;
      }
      if (fakeMouseUp) {
        sendMouseEvent(MouseUp, button, handle, msg, wParam, lParam);
      }
    }
    dragStarted = false;
    return new LRESULT(code);
  }
}
