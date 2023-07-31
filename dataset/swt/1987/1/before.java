class PlaceHold {
  public void setExpanded(boolean expanded) {
    checkWidget();
    int hwnd = parent.handle;
    if (OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_CHILD, handle) == 0) {
      return;
    }
    int state = 0;
    if (OS.IsWinCE) {
      TVITEM tvItem = new TVITEM();
      tvItem.hItem = handle;
      tvItem.mask = OS.TVIF_STATE;
      OS.SendMessage(hwnd, TVM_GETITEM, 0, tvItem);
      state = tvItem.state;
    } else {
      state = ((int) (OS.SendMessage(hwnd, TVM_GETITEMSTATE, handle, TVIS_EXPANDED)));
    }
    if (((state & OS.TVIS_EXPANDED) != 0) == expanded) {
      return;
    }
    RECT oldRect = null;
    RECT[] rects = null;
    SCROLLINFO oldInfo = null;
    int count = 0;
    int hBottomItem = 0;
    boolean redraw = false;
    boolean noScroll = true;
    int hTopItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
    if (noScroll && (hTopItem != 0)) {
      oldInfo = new SCROLLINFO();
      oldInfo.cbSize = SCROLLINFO.sizeof;
      oldInfo.fMask = OS.SIF_ALL;
      if (!OS.GetScrollInfo(hwnd, SB_HORZ, oldInfo)) {
        oldInfo = null;
      }
      if (parent.getDrawing() && OS.IsWindowVisible(hwnd)) {
        boolean noAnimate = true;
        count = ((int) (OS.SendMessage(hwnd, TVM_GETVISIBLECOUNT, 0, 0)));
        rects = new RECT[count + 1];
        int hItem = hTopItem;
        int index = 0;
        while (((hItem != 0) && (noAnimate || (hItem != handle))) && (index < count)) {
          RECT rect = new RECT();
          if (OS.TreeView_GetItemRect(hwnd, hItem, rect, true)) {
            rects[index++] = rect;
          }
          hItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_NEXTVISIBLE, hItem);
        }
        if (noAnimate || (hItem != handle)) {
          redraw = true;
          count = index;
          hBottomItem = hItem;
          oldRect = new RECT();
          OS.GetClientRect(hwnd, oldRect);
          int topHandle = parent.topHandle();
          OS.UpdateWindow(topHandle);
          OS.DefWindowProc(topHandle, WM_SETREDRAW, 0, 0);
          if (hwnd != topHandle) {
            OS.UpdateWindow(hwnd);
            OS.DefWindowProc(hwnd, WM_SETREDRAW, 0, 0);
          }
        }
      }
    }
    int hOldItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_CARET, 0);
    parent.ignoreExpand = true;
    OS.SendMessage(hwnd, TVM_EXPAND, expanded ? OS.TVE_EXPAND : OS.TVE_COLLAPSE, handle);
    parent.ignoreExpand = false;
    if (noScroll && (hTopItem != 0)) {
      boolean collapsed = false;
      if (!expanded) {
        RECT rect = new RECT();
        while ((hTopItem != 0) && (!OS.TreeView_GetItemRect(hwnd, hTopItem, rect, false))) {
          hTopItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_PARENT, hTopItem);
          collapsed = true;
        }
      }
      boolean scrolled = true;
      if (hTopItem != 0) {
        OS.SendMessage(hwnd, TVM_SELECTITEM, TVGN_FIRSTVISIBLE, hTopItem);
        scrolled = hTopItem != OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
      }
      if (((!collapsed) && (!scrolled)) && (oldInfo != null)) {
        SCROLLINFO newInfo = new SCROLLINFO();
        newInfo.cbSize = SCROLLINFO.sizeof;
        newInfo.fMask = OS.SIF_ALL;
        if (OS.GetScrollInfo(hwnd, SB_HORZ, newInfo)) {
          if (oldInfo.nPos != newInfo.nPos) {
            int lParam = OS.MAKELPARAM(SB_THUMBPOSITION, oldInfo.nPos);
            OS.SendMessage(hwnd, WM_HSCROLL, lParam, 0);
          }
        }
      }
      if (redraw) {
        boolean fixScroll = false;
        if ((!collapsed) && (!scrolled)) {
          RECT newRect = new RECT();
          OS.GetClientRect(hwnd, newRect);
          if (OS.EqualRect(oldRect, newRect)) {
            int hItem = hTopItem;
            int index = 0;
            while ((hItem != 0) && (index < count)) {
              RECT rect = new RECT();
              if (OS.TreeView_GetItemRect(hwnd, hItem, rect, true)) {
                if (!OS.EqualRect(rect, rects[index])) {
                  break;
                }
              }
              hItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_NEXTVISIBLE, hItem);
              index++;
            }
            fixScroll = (index == count) && (hItem == hBottomItem);
          }
        }
        int topHandle = parent.topHandle();
        OS.DefWindowProc(topHandle, WM_SETREDRAW, 1, 0);
        if (hwnd != topHandle) {
          OS.DefWindowProc(hwnd, WM_SETREDRAW, 1, 0);
        }
        if (fixScroll) {
          parent.updateScrollBar();
          SCROLLINFO info = new SCROLLINFO();
          info.cbSize = SCROLLINFO.sizeof;
          info.fMask = OS.SIF_ALL;
          if (OS.GetScrollInfo(hwnd, SB_VERT, info)) {
            OS.SetScrollInfo(hwnd, SB_VERT, info, true);
          }
          if (handle == hBottomItem) {
            RECT rect = new RECT();
            if (OS.TreeView_GetItemRect(hwnd, hBottomItem, rect, false)) {
              OS.InvalidateRect(hwnd, rect, true);
            }
          }
        } else if (OS.IsWinCE) {
          OS.InvalidateRect(topHandle, null, true);
          if (hwnd != topHandle) {
            OS.InvalidateRect(hwnd, null, true);
          }
        } else {
          int flags = ((OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE) | OS.RDW_ALLCHILDREN;
          OS.RedrawWindow(topHandle, null, 0, flags);
        }
      }
    }
    int hNewItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_CARET, 0);
    if (hNewItem != hOldItem) {
      Event event = new Event();
      if (hNewItem != 0) {
        event.item = parent._getItem(hNewItem);
        parent.hAnchor = hNewItem;
      }
      parent.sendEvent(Selection, event);
    }
  }
}
