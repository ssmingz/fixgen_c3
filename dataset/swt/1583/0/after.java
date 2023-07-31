class PlaceHold {
  LRESULT WM_KEYDOWN(int wParam, int lParam) {
    LRESULT result = super.WM_KEYDOWN(wParam, lParam);
    if (result != null) {
      return result;
    }
    switch (wParam) {
      case OS.VK_SPACE:
        return LRESULT.ZERO;
      case OS.VK_UP:
      case OS.VK_DOWN:
      case OS.VK_PRIOR:
      case OS.VK_NEXT:
      case OS.VK_HOME:
      case OS.VK_END:
        {
          OS.SendMessage(handle, WM_CHANGEUISTATE, UIS_INITIALIZE, 0);
          if ((style & SWT.SINGLE) != 0) {
            break;
          }
          if (OS.GetKeyState(VK_SHIFT) < 0) {
            int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
            if (hItem != 0) {
              if (hAnchor == 0) {
                hAnchor = hItem;
              }
              ignoreSelect = ignoreDeselect = true;
              int code = callWindowProc(handle, WM_KEYDOWN, wParam, lParam);
              ignoreSelect = ignoreDeselect = false;
              int hNewItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
              TVITEM tvItem = new TVITEM();
              tvItem.mask = OS.TVIF_STATE;
              tvItem.stateMask = OS.TVIS_SELECTED;
              int hDeselectItem = hItem;
              RECT rect1 = new RECT();
              rect1.left = hAnchor;
              OS.SendMessage(handle, TVM_GETITEMRECT, 1, rect1);
              RECT rect2 = rect2 = new RECT();
              rect2.left = hDeselectItem;
              OS.SendMessage(handle, TVM_GETITEMRECT, 1, rect2);
              int flags = (rect1.top < rect2.top) ? OS.TVGN_PREVIOUSVISIBLE : OS.TVGN_NEXTVISIBLE;
              while (hDeselectItem != hAnchor) {
                tvItem.hItem = hDeselectItem;
                OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
                hDeselectItem = OS.SendMessage(handle, TVM_GETNEXTITEM, flags, hDeselectItem);
              }
              int hSelectItem = hAnchor;
              rect1.left = hNewItem;
              OS.SendMessage(handle, TVM_GETITEMRECT, 1, rect1);
              rect2.left = hSelectItem;
              OS.SendMessage(handle, TVM_GETITEMRECT, 1, rect2);
              tvItem.state = OS.TVIS_SELECTED;
              flags = (rect1.top < rect2.top) ? OS.TVGN_PREVIOUSVISIBLE : OS.TVGN_NEXTVISIBLE;
              while (hSelectItem != hNewItem) {
                tvItem.hItem = hSelectItem;
                OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
                hSelectItem = OS.SendMessage(handle, TVM_GETNEXTITEM, flags, hSelectItem);
              }
              tvItem.hItem = hNewItem;
              OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
              tvItem.mask = OS.TVIF_PARAM;
              tvItem.hItem = hNewItem;
              OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
              Event event = new Event();
              event.item = _getItem(hNewItem, tvItem.lParam);
              postEvent(Selection, event);
              return new LRESULT(code);
            }
          }
          if (OS.GetKeyState(VK_CONTROL) < 0) {
            int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
            if (hItem != 0) {
              TVITEM tvItem = new TVITEM();
              tvItem.mask = OS.TVIF_STATE;
              tvItem.stateMask = OS.TVIS_SELECTED;
              tvItem.hItem = hItem;
              OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
              boolean oldSelected = (tvItem.state & OS.TVIS_SELECTED) != 0;
              int hNewItem = 0;
              switch (wParam) {
                case OS.VK_UP:
                  hNewItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_PREVIOUSVISIBLE, hItem);
                  break;
                case OS.VK_DOWN:
                  hNewItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_NEXTVISIBLE, hItem);
                  break;
                case OS.VK_HOME:
                  hNewItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
                  break;
                case OS.VK_PRIOR:
                  hNewItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
                  if (hNewItem == hItem) {
                    OS.SendMessage(handle, WM_VSCROLL, SB_PAGEUP, 0);
                    hNewItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
                  }
                  break;
                case OS.VK_NEXT:
                  RECT rect = new RECT();
                  RECT clientRect = new RECT();
                  OS.GetClientRect(handle, clientRect);
                  hNewItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
                  do {
                    int hVisible =
                        OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_NEXTVISIBLE, hNewItem);
                    if (hVisible == 0) {
                      break;
                    }
                    rect.left = hVisible;
                    OS.SendMessage(handle, TVM_GETITEMRECT, 1, rect);
                    if (rect.bottom > clientRect.bottom) {
                      break;
                    }
                    if ((hNewItem = hVisible) == hItem) {
                      OS.SendMessage(handle, WM_VSCROLL, SB_PAGEDOWN, 0);
                    }
                  } while (hNewItem != 0);
                  break;
                case OS.VK_END:
                  hNewItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_LASTVISIBLE, 0);
                  break;
              }
              if (hNewItem != 0) {
                OS.SendMessage(handle, TVM_ENSUREVISIBLE, 0, hNewItem);
                tvItem.hItem = hNewItem;
                OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
                boolean newSelected = (tvItem.state & OS.TVIS_SELECTED) != 0;
                if ((!newSelected) && (drawCount == 0)) {
                  OS.UpdateWindow(handle);
                  OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
                }
                ignoreSelect = true;
                OS.SendMessage(handle, TVM_SELECTITEM, TVGN_CARET, hNewItem);
                ignoreSelect = false;
                if (oldSelected) {
                  tvItem.state = OS.TVIS_SELECTED;
                  tvItem.hItem = hItem;
                  OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
                }
                if (!newSelected) {
                  tvItem.state = 0;
                  tvItem.hItem = hNewItem;
                  OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
                }
                if ((!newSelected) && (drawCount == 0)) {
                  RECT rect1 = new RECT();
                  RECT rect2 = new RECT();
                  rect1.left = hItem;
                  rect2.left = hNewItem;
                  int bits = OS.GetWindowLong(handle, GWL_STYLE);
                  int fItemRect = ((bits & OS.TVS_FULLROWSELECT) != 0) ? 0 : 1;
                  OS.SendMessage(handle, TVM_GETITEMRECT, fItemRect, rect1);
                  OS.SendMessage(handle, TVM_GETITEMRECT, fItemRect, rect2);
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
                return LRESULT.ZERO;
              }
            }
          }
          int code = callWindowProc(handle, WM_KEYDOWN, wParam, lParam);
          hAnchor = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
          return new LRESULT(code);
        }
    }
    return result;
  }
}
