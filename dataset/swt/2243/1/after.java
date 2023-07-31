class PlaceHold {
  void sendEraseItemEvent(TableItem item, NMLVCUSTOMDRAW nmcd, int lParam) {
    int hDC = nmcd.hdc;
    int clrText = (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
    if (clrText == (-1)) {
      clrText = item.foreground;
    }
    int clrTextBk = -1;
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      if ((sortColumn != null) && (sortDirection != SWT.NONE)) {
        if (findImageControl() == null) {
          if (indexOf(sortColumn) == nmcd.iSubItem) {
            clrTextBk = getSortColumnPixel();
          }
        }
      }
    }
    clrTextBk = (item.cellBackground != null) ? item.cellBackground[nmcd.iSubItem] : -1;
    if (clrTextBk == (-1)) {
      clrTextBk = item.background;
    }
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_STATE;
    lvItem.stateMask = OS.LVIS_SELECTED;
    lvItem.iItem = ((int) (nmcd.dwItemSpec));
    int result = OS.SendMessage(handle, LVM_GETITEM, 0, lvItem);
    boolean selected = (result != 0) && ((lvItem.state & OS.LVIS_SELECTED) != 0);
    GCData data = new GCData();
    data.device = display;
    int clrSelectionBk = -1;
    boolean drawSelected = false;
    boolean drawBackground = false;
    boolean drawHot = false;
    if ((nmcd.iSubItem == 0) || ((style & SWT.FULL_SELECTION) != 0)) {
      drawHot = hotIndex == nmcd.dwItemSpec;
    }
    if (OS.IsWindowEnabled(handle)) {
      if (selected && ((nmcd.iSubItem == 0) || ((style & SWT.FULL_SELECTION) != 0))) {
        if (OS.GetFocus() == handle) {
          drawSelected = true;
          data.foreground = OS.GetSysColor(COLOR_HIGHLIGHTTEXT);
          data.background = clrSelectionBk = OS.GetSysColor(COLOR_HIGHLIGHT);
        } else {
          drawSelected = (style & SWT.HIDE_SELECTION) == 0;
          data.foreground = OS.GetTextColor(hDC);
          data.background = clrSelectionBk = OS.GetSysColor(COLOR_3DFACE);
        }
        if (explorerTheme) {
          data.foreground = (clrText != (-1)) ? clrText : getForegroundPixel();
        }
      } else {
        drawBackground = clrTextBk != (-1);
        if (clrTextBk == (-1)) {
          Control control = findBackgroundControl();
          if (control == null) {
            control = this;
          }
          clrTextBk = control.getBackgroundPixel();
        }
        data.foreground = (clrText != (-1)) ? clrText : OS.GetTextColor(hDC);
        data.background = (clrTextBk != (-1)) ? clrTextBk : OS.GetBkColor(hDC);
      }
    } else {
      data.foreground = OS.GetSysColor(COLOR_GRAYTEXT);
      data.background = OS.GetSysColor(COLOR_3DFACE);
      if (selected) {
        clrSelectionBk = data.background;
      }
    }
    data.hFont = item.fontHandle(nmcd.iSubItem);
    data.uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
    int nSavedDC = OS.SaveDC(hDC);
    GC gc = GC.win32_new(hDC, data);
    RECT cellRect =
        item.getBounds(((int) (nmcd.dwItemSpec)), nmcd.iSubItem, true, true, true, true, hDC);
    Event event = new Event();
    event.item = item;
    event.gc = gc;
    event.index = nmcd.iSubItem;
    event.detail |= SWT.FOREGROUND;
    if (OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED) == nmcd.dwItemSpec) {
      if ((nmcd.iSubItem == 0) || ((style & SWT.FULL_SELECTION) != 0)) {
        if (handle == OS.GetFocus()) {
          int uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
          if ((uiState & OS.UISF_HIDEFOCUS) == 0) {
            event.detail |= SWT.FOCUSED;
          }
        }
      }
    }
    if (drawHot) {
      event.detail |= SWT.HOT;
    }
    if (drawSelected) {
      event.detail |= SWT.SELECTED;
    }
    if (drawBackground) {
      event.detail |= SWT.BACKGROUND;
    }
    event.x = cellRect.left;
    event.y = cellRect.top;
    event.width = cellRect.right - cellRect.left;
    event.height = cellRect.bottom - cellRect.top;
    gc.setClipping(event.x, event.y, event.width, event.height);
    sendEvent(EraseItem, event);
    event.gc = null;
    int clrSelectionText = data.foreground;
    gc.dispose();
    OS.RestoreDC(hDC, nSavedDC);
    if (isDisposed() || item.isDisposed()) {
      return;
    }
    if (event.doit) {
      ignoreDrawForeground = (event.detail & SWT.FOREGROUND) == 0;
      ignoreDrawBackground = (event.detail & SWT.BACKGROUND) == 0;
      ignoreDrawSelection = (event.detail & SWT.SELECTED) == 0;
      ignoreDrawFocus = (event.detail & SWT.FOCUSED) == 0;
      ignoreDrawHot = (event.detail & SWT.HOT) == 0;
    } else {
      ignoreDrawForeground =
          ignoreDrawBackground = ignoreDrawSelection = ignoreDrawFocus = ignoreDrawHot = true;
    }
    if (drawSelected) {
      if (ignoreDrawSelection) {
        ignoreDrawHot = true;
        if ((nmcd.iSubItem == 0) || ((style & SWT.FULL_SELECTION) != 0)) {
          selectionForeground = clrSelectionText;
        }
        nmcd.uItemState &= ~OS.CDIS_SELECTED;
        OS.MoveMemory(lParam, nmcd, sizeof);
      }
    } else if (ignoreDrawSelection) {
      nmcd.uItemState |= OS.CDIS_SELECTED;
      OS.MoveMemory(lParam, nmcd, sizeof);
    }
    if (ignoreDrawFocus) {
      nmcd.uItemState &= ~OS.CDIS_FOCUS;
      OS.MoveMemory(lParam, nmcd, sizeof);
    }
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    boolean firstColumn = nmcd.iSubItem == OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, 0, 0);
    if (ignoreDrawForeground && ignoreDrawHot) {
      if ((!ignoreDrawBackground) && drawBackground) {
        RECT backgroundRect =
            item.getBounds(((int) (nmcd.dwItemSpec)), nmcd.iSubItem, true, false, true, false, hDC);
        fillBackground(hDC, clrTextBk, backgroundRect);
      }
    }
    if ((!ignoreDrawHot) || ((!ignoreDrawSelection) && (clrSelectionBk != (-1)))) {
      if (explorerTheme) {
        boolean hot = drawHot;
        RECT pClipRect = new RECT();
        OS.SetRect(pClipRect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
        RECT rect = new RECT();
        OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
        if ((style & SWT.FULL_SELECTION) != 0) {
          int count = ((int) (OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0)));
          int index = ((int) (OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, count - 1, 0)));
          RECT headerRect = new RECT();
          OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect);
          OS.MapWindowPoints(hwndHeader, handle, headerRect, 2);
          rect.left = 0;
          rect.right = headerRect.right;
          pClipRect.left = cellRect.left;
          pClipRect.right += EXPLORER_EXTRA;
        } else {
          rect.right += EXPLORER_EXTRA;
          pClipRect.right += EXPLORER_EXTRA;
        }
        int hTheme = OS.OpenThemeData(handle, LISTVIEW);
        int iStateId = (selected) ? OS.LISS_SELECTED : OS.LISS_HOT;
        if (((OS.GetFocus() != handle) && selected) && (!hot)) {
          iStateId = OS.LISS_SELECTEDNOTFOCUS;
        }
        OS.DrawThemeBackground(hTheme, hDC, LVP_LISTITEM, iStateId, rect, pClipRect);
        OS.CloseThemeData(hTheme);
      } else {
        boolean fullText = ((style & SWT.FULL_SELECTION) != 0) || (!firstColumn);
        RECT textRect =
            item.getBounds(
                ((int) (nmcd.dwItemSpec)), nmcd.iSubItem, true, false, fullText, false, hDC);
        fillBackground(hDC, clrSelectionBk, textRect);
      }
    }
    if (ignoreDrawForeground) {
      RECT clipRect =
          item.getBounds(((int) (nmcd.dwItemSpec)), nmcd.iSubItem, true, true, true, false, hDC);
      OS.SaveDC(hDC);
      OS.SelectClipRgn(hDC, 0);
      OS.ExcludeClipRect(hDC, clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
    }
  }
}
