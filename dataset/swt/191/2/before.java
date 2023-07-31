class PlaceHold {
  LRESULT wmNotifyChild(NMHDR hdr, int wParam, int lParam) {
    switch (hdr.code) {
      case OS.TBN_DROPDOWN:
        NMTOOLBAR lpnmtb = new NMTOOLBAR();
        OS.MoveMemory(lpnmtb, lParam, sizeof);
        ToolItem child = items[lpnmtb.iItem];
        if (child != null) {
          Event event = new Event();
          event.detail = SWT.ARROW;
          int index = ((int) (OS.SendMessage(handle, TB_COMMANDTOINDEX, lpnmtb.iItem, 0)));
          RECT rect = new RECT();
          OS.SendMessage(handle, TB_GETITEMRECT, index, rect);
          event.x = rect.left;
          event.y = rect.bottom;
          child.postEvent(Selection, event);
        }
        break;
      case OS.NM_CUSTOMDRAW:
        if (OS.COMCTL32_MAJOR < 6) {
          break;
        }
        NMCUSTOMDRAW nmcd = new NMCUSTOMDRAW();
        OS.MoveMemory(nmcd, lParam, sizeof);
        switch (nmcd.dwDrawStage) {
          case OS.CDDS_PREERASE:
            {
              int bits = OS.GetWindowLong(handle, GWL_STYLE);
              if ((bits & OS.TBSTYLE_FLAT) == 0) {
                drawBackground(nmcd.hdc);
              } else {
                RECT rect = new RECT();
                OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                drawBackground(nmcd.hdc, rect);
              }
              return new LRESULT(OS.CDRF_SKIPDEFAULT);
            }
        }
        break;
      case OS.TBN_HOTITEMCHANGE:
        if (!OS.IsWinCE) {
          NMTBHOTITEM lpnmhi = new NMTBHOTITEM();
          OS.MoveMemory(lpnmhi, lParam, sizeof);
          switch (lpnmhi.dwFlags) {
            case OS.HICF_MOUSE:
              {
                if (lastArrowId != (-1)) {
                  return LRESULT.ONE;
                }
                break;
              }
            case OS.HICF_ARROWKEYS:
              {
                RECT client = new RECT();
                OS.GetClientRect(handle, client);
                int index = ((int) (OS.SendMessage(handle, TB_COMMANDTOINDEX, lpnmhi.idNew, 0)));
                RECT rect = new RECT();
                OS.SendMessage(handle, TB_GETITEMRECT, index, rect);
                if ((rect.right > client.right) || (rect.bottom > client.bottom)) {
                  return LRESULT.ONE;
                }
                lastArrowId = lpnmhi.idNew;
                break;
              }
            default:
              lastArrowId = -1;
          }
          if ((lpnmhi.dwFlags & OS.HICF_LEAVING) == 0) {
            lastHotId = lpnmhi.idNew;
          }
        }
        break;
    }
    return super.wmNotifyChild(hdr, wParam, lParam);
  }
}
