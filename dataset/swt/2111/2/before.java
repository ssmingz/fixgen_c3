class PlaceHold {
  LRESULT WM_LBUTTONDOWN(int wParam, int lParam) {
    TVHITTESTINFO lpht = new TVHITTESTINFO();
    lpht.x = ((short) (lParam & 0xffff));
    lpht.y = ((short) (lParam >> 16));
    OS.SendMessage(handle, TVM_HITTEST, 0, lpht);
    if ((lpht.hItem == 0) || ((lpht.flags & OS.TVHT_ONITEM) == 0)) {
      sendMouseEvent(MouseDown, 1, WM_LBUTTONDOWN, wParam, lParam);
      int code = callWindowProc(WM_LBUTTONDOWN, wParam, lParam);
      if (OS.GetCapture() != handle) {
        OS.SetCapture(handle);
      }
      return new LRESULT(code);
    }
    if ((style & SWT.CHECK) != 0) {
      if ((lpht.flags & OS.TVHT_ONITEMSTATEICON) != 0) {
        TVITEM tvItem = new TVITEM();
        tvItem.hItem = lpht.hItem;
        tvItem.mask = OS.TVIF_PARAM | OS.TVIF_STATE;
        tvItem.stateMask = OS.TVIS_STATEIMAGEMASK;
        OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
        int state = tvItem.state >> 12;
        if ((state & 0x1) != 0) {
          state++;
        } else {
          --state;
        }
        tvItem.state = state << 12;
        OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
        Event event = new Event();
        event.item = items[tvItem.lParam];
        event.detail = SWT.CHECK;
        postEvent(Selection, event);
        sendMouseEvent(MouseDown, 1, WM_LBUTTONDOWN, wParam, lParam);
        if (OS.GetCapture() != handle) {
          OS.SetCapture(handle);
        }
        return LRESULT.ZERO;
      }
    }
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_STATE;
    tvItem.stateMask = OS.TVIS_SELECTED;
    boolean hittestSelected = false;
    if ((style & SWT.MULTI) != 0) {
      tvItem.hItem = lpht.hItem;
      OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
      hittestSelected = (tvItem.state & OS.TVIS_SELECTED) != 0;
    }
    int hOldItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
    if ((style & SWT.MULTI) != 0) {
      tvItem.hItem = hOldItem;
      OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
      if (hittestSelected || ((wParam & OS.MK_CONTROL) != 0)) {
        if (drawCount == 0) {
          OS.UpdateWindow(handle);
          OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
        }
      } else {
        deselectAll();
      }
    }
    sendMouseEvent(MouseDown, 1, WM_LBUTTONDOWN, wParam, lParam);
    dragStarted = false;
    ignoreDeselect = ignoreSelect = true;
    int code = callWindowProc(WM_LBUTTONDOWN, wParam, lParam);
    ignoreDeselect = ignoreSelect = false;
    if (dragStarted && (OS.GetCapture() != handle)) {
      OS.SetCapture(handle);
    }
    int hNewItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
    if ((style & SWT.SINGLE) != 0) {
      if (hOldItem == hNewItem) {
        tvItem.mask = OS.TVIF_STATE;
        tvItem.state = OS.TVIS_SELECTED;
        tvItem.stateMask = OS.TVIS_SELECTED;
        tvItem.hItem = hNewItem;
        OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
      }
    }
    if ((style & SWT.MULTI) != 0) {
      if (hittestSelected || ((wParam & OS.MK_CONTROL) != 0)) {
        if ((hOldItem == hNewItem) && (hOldItem == lpht.hItem)) {
          if ((wParam & OS.MK_CONTROL) != 0) {
            tvItem.state ^= OS.TVIS_SELECTED;
            if (dragStarted) {
              tvItem.state = OS.TVIS_SELECTED;
            }
            OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
          }
        } else {
          if ((tvItem.state & OS.TVIS_SELECTED) != 0) {
            tvItem.state = OS.TVIS_SELECTED;
            OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
          }
          if (((wParam & OS.MK_CONTROL) != 0) && (!dragStarted)) {
            if (hittestSelected) {
              tvItem.state = 0;
              tvItem.hItem = lpht.hItem;
              OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
            }
          }
        }
        if (drawCount == 0) {
          RECT rect1 = new RECT();
          RECT rect2 = new RECT();
          rect1.left = hOldItem;
          rect2.left = hNewItem;
          OS.SendMessage(handle, TVM_GETITEMRECT, 1, rect1);
          OS.SendMessage(handle, TVM_GETITEMRECT, 1, rect2);
          OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
          if (OS.IsWinCE) {
            OS.InvalidateRect(handle, rect1, false);
            OS.InvalidateRect(handle, rect2, false);
            OS.UpdateWindow(handle);
          } else {
            int flags = OS.RDW_UPDATENOW | OS.RDW_INVALIDATE;
            OS.RedrawWindow(handle, rect1, 0, flags);
            OS.RedrawWindow(handle, rect2, 0, flags);
          }
        }
      }
      if ((wParam & OS.MK_CONTROL) == 0) {
        if ((!hittestSelected) || (!dragStarted)) {
          tvItem.state = 0;
          int oldProc = OS.GetWindowLong(handle, GWL_WNDPROC);
          OS.SetWindowLong(handle, GWL_WNDPROC, TreeProc);
          for (int i = 0; i < items.length; i++) {
            TreeItem item = items[i];
            if ((item != null) && (item.handle != hNewItem)) {
              tvItem.hItem = item.handle;
              OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
            }
          }
          tvItem.hItem = hNewItem;
          tvItem.state = OS.TVIS_SELECTED;
          OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
          OS.SetWindowLong(handle, GWL_WNDPROC, oldProc);
          if ((wParam & OS.MK_SHIFT) != 0) {
            RECT rect1 = new RECT();
            if (hAnchor == 0) {
              hAnchor = hNewItem;
            }
            rect1.left = hAnchor;
            if (OS.SendMessage(handle, TVM_GETITEMRECT, 1, rect1) != 0) {
              RECT rect2 = rect2 = new RECT();
              rect2.left = hNewItem;
              OS.SendMessage(handle, TVM_GETITEMRECT, 1, rect2);
              int flags = (rect1.top < rect2.top) ? OS.TVGN_NEXTVISIBLE : OS.TVGN_PREVIOUSVISIBLE;
              tvItem.state = OS.TVIS_SELECTED;
              int hItem = tvItem.hItem = hAnchor;
              OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
              while (hItem != hNewItem) {
                tvItem.hItem = hItem;
                OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
                hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, flags, hItem);
              }
            }
          }
        }
      }
    }
    if ((wParam & OS.MK_SHIFT) == 0) {
      hAnchor = hNewItem;
    }
    tvItem.hItem = hNewItem;
    tvItem.mask = OS.TVIF_PARAM;
    OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
    Event event = new Event();
    event.item = items[tvItem.lParam];
    postEvent(Selection, event);
    if (dragStarted) {
      postEvent(DragDetect);
    } else {
      sendMouseEvent(MouseUp, 1, WM_LBUTTONUP, wParam, lParam);
    }
    dragStarted = false;
    return new LRESULT(code);
  }
}
