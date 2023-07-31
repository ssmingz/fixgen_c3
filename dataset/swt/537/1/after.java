class PlaceHold {
  LRESULT CDDS_ITEMPREPAINT(int wParam, int lParam) {
    NMTVCUSTOMDRAW nmcd = new NMTVCUSTOMDRAW();
    OS.MoveMemory(nmcd, lParam, sizeof);
    int id = nmcd.lItemlParam;
    if ((style & SWT.VIRTUAL) != 0) {
      if (id == (-1)) {
        TVITEM tvItem = new TVITEM();
        tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
        tvItem.hItem = nmcd.dwItemSpec;
        OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
        id = tvItem.lParam;
      }
    }
    TreeItem item = _getItem(nmcd.dwItemSpec, id);
    if (item == null) {
      return null;
    }
    RECT clipRect = null;
    int index = 0;
    int count = 0;
    if (hwndHeader != 0) {
      index = OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, 0, 0);
      count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
      if (count != 0) {
        clipRect = new RECT();
        HDITEM hdItem = new HDITEM();
        hdItem.mask = OS.HDI_WIDTH;
        OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
        OS.SetRect(clipRect, nmcd.left, nmcd.top, nmcd.left + hdItem.cxy, nmcd.bottom);
      }
    }
    int clrText = -1;
    int clrTextBk = -1;
    int hFont = (item.cellFont != null) ? item.cellFont[index] : -1;
    if (hFont == (-1)) {
      hFont = item.font;
    }
    if (OS.IsWindowEnabled(handle)) {
      clrText = (item.cellForeground != null) ? item.cellForeground[index] : -1;
      if (clrText == (-1)) {
        clrText = item.foreground;
      }
      clrTextBk = (item.cellBackground != null) ? item.cellBackground[index] : -1;
      if (clrTextBk == (-1)) {
        clrTextBk = item.background;
      }
    }
    int clrSortBk = -1;
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      if ((sortColumn != null) && (sortDirection != SWT.NONE)) {
        if (findImageControl() == null) {
          if (indexOf(sortColumn) == index) {
            clrTextBk = clrSortBk = getSortColumnPixel();
          }
        }
      }
    }
    int hDC = nmcd.hdc;
    boolean selected = false;
    if (OS.IsWindowEnabled(handle)) {
      TVITEM tvItem = new TVITEM();
      tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_STATE;
      tvItem.hItem = item.handle;
      OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
      if ((tvItem.state & (OS.TVIS_SELECTED | OS.TVIS_DROPHILITED)) != 0) {
        selected = true;
        if (handle == OS.GetFocus()) {
          if (OS.GetTextColor(hDC) != OS.GetSysColor(COLOR_HIGHLIGHTTEXT)) {
            selected = false;
          } else if (OS.GetBkColor(hDC) != OS.GetSysColor(COLOR_HIGHLIGHT)) {
            selected = false;
          }
        }
      }
    }
    if ((OS.IsWindowVisible(handle) && (nmcd.left < nmcd.right)) && (nmcd.top < nmcd.bottom)) {
      if (linesVisible) {
        RECT rect = new RECT();
        OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
        OS.DrawEdge(hDC, rect, BDR_SUNKENINNER, BF_BOTTOM);
      }
      if (hooks(MeasureItem)) {
        RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
        int nSavedDC = OS.SaveDC(hDC);
        GCData data = new GCData();
        data.device = display;
        data.hFont = hFont;
        GC gc = GC.win32_new(hDC, data);
        Event event = new Event();
        event.item = item;
        event.gc = gc;
        event.index = index;
        event.x = itemRect.left;
        event.y = itemRect.top;
        event.width = itemRect.right - itemRect.left;
        event.height = itemRect.bottom - itemRect.top;
        sendEvent(MeasureItem, event);
        event.gc = null;
        gc.dispose();
        OS.RestoreDC(hDC, nSavedDC);
        if (isDisposed() || item.isDisposed()) {
          return null;
        }
        if (hwndHeader != 0) {
          if (count == 0) {
            if ((event.x + event.width) > scrollWidth) {
              setScrollWidth(scrollWidth = event.x + event.width);
            }
          }
        }
        if (event.height > getItemHeight()) {
          setItemHeight(event.height);
        }
      }
      selectionForeground = -1;
      ignoreDraw = ignoreDrawSelection = ignoreDrawBackground = false;
      if (hooks(EraseItem)) {
        RECT rect = new RECT();
        OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
        drawBackground(hDC, rect);
        RECT cellRect = null;
        if (((style & SWT.FULL_SELECTION) != 0) && (count == 0)) {
          cellRect = item.getBounds(index, true, true, true, true, true, hDC);
        } else {
          cellRect = item.getBounds(index, true, true, false, false, true, hDC);
        }
        if (clrSortBk != (-1)) {
          RECT fullRect = item.getBounds(index, true, true, true, true, true, hDC);
          drawBackground(hDC, fullRect, clrSortBk);
        }
        int nSavedDC = OS.SaveDC(hDC);
        GCData data = new GCData();
        data.device = display;
        data.foreground = OS.GetTextColor(hDC);
        data.background = OS.GetBkColor(hDC);
        if (!selected) {
          if (clrText != (-1)) {
            data.foreground = clrText;
          }
          if (clrTextBk != (-1)) {
            data.background = clrTextBk;
          }
        }
        data.hPen = OS.CreatePen(PS_SOLID, 0, data.foreground);
        data.hBrush = OS.CreateSolidBrush(data.background);
        if (hFont != (-1)) {
          data.hFont = hFont;
        }
        OS.SelectObject(hDC, data.hPen);
        OS.SelectObject(hDC, data.hBrush);
        GC gc = GC.win32_new(hDC, data);
        Event event = new Event();
        event.index = index;
        event.item = item;
        event.gc = gc;
        event.detail |= SWT.FOREGROUND;
        if (clrTextBk != (-1)) {
          event.detail |= SWT.BACKGROUND;
        }
        if (selected) {
          event.detail |= SWT.SELECTED;
        }
        if ((nmcd.uItemState & OS.CDIS_FOCUS) != 0) {
          if ((style & SWT.FULL_SELECTION) != 0) {
            if (handle == OS.GetFocus()) {
              int uiState = OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0);
              if ((uiState & OS.UISF_HIDEFOCUS) == 0) {
                event.detail |= SWT.FOCUSED;
              }
            }
          }
        }
        event.x = cellRect.left;
        event.y = cellRect.top;
        event.width = cellRect.right - cellRect.left;
        event.height = cellRect.bottom - cellRect.top;
        gc.setClipping(event.x, event.y, event.width, event.height);
        sendEvent(EraseItem, event);
        event.gc = null;
        int newTextClr = OS.GetTextColor(hDC);
        gc.dispose();
        OS.RestoreDC(hDC, nSavedDC);
        if (isDisposed() || item.isDisposed()) {
          return null;
        }
        if (event.doit) {
          ignoreDraw = (event.detail & SWT.FOREGROUND) == 0;
          ignoreDrawSelection = (event.detail & SWT.SELECTED) == 0;
          ignoreDrawBackground = (event.detail & SWT.BACKGROUND) == 0;
        } else {
          ignoreDraw = ignoreDrawSelection = ignoreDrawBackground = true;
        }
        if (((!selected) && (!ignoreDrawBackground)) && (clrTextBk != (-1))) {
          if (((style & SWT.FULL_SELECTION) != 0) && (count == 0)) {
            fillBackground(hDC, clrTextBk, rect);
          } else {
            RECT backgroundRect = item.getBounds(index, true, false, true, false, true, hDC);
            fillBackground(hDC, clrTextBk, backgroundRect);
          }
        }
        if (!ignoreDrawSelection) {
          if (!selected) {
            selectionForeground = clrText = OS.GetSysColor(COLOR_HIGHLIGHTTEXT);
          }
          if ((style & SWT.FULL_SELECTION) != 0) {
            fillBackground(hDC, OS.GetBkColor(hDC), rect);
          } else {
            RECT textRect = item.getBounds(index, true, false, false, false, true, hDC);
            fillBackground(hDC, OS.GetBkColor(hDC), textRect);
          }
        } else if (selected) {
          selectionForeground = clrText = newTextClr;
          ignoreDrawSelection = true;
        }
        RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
        OS.SaveDC(hDC);
        OS.SelectClipRgn(hDC, 0);
        itemRect.right++;
        if (linesVisible) {
          itemRect.bottom++;
        }
        if (clipRect != null) {
          OS.IntersectClipRect(hDC, clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
        }
        OS.ExcludeClipRect(hDC, itemRect.left, itemRect.top, itemRect.right, itemRect.bottom);
        return new LRESULT(OS.CDRF_DODEFAULT | OS.CDRF_NOTIFYPOSTPAINT);
      }
      if (selected) {
        if ((style & SWT.FULL_SELECTION) != 0) {
          int bits = OS.GetWindowLong(handle, GWL_STYLE);
          if ((bits & OS.TVS_FULLROWSELECT) == 0) {
            RECT rect = new RECT();
            OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
            fillBackground(hDC, OS.GetBkColor(hDC), rect);
          }
        }
      }
    }
    LRESULT result = null;
    if (((clrText == (-1)) && (clrTextBk == (-1))) && (hFont == (-1))) {
      result = new LRESULT(OS.CDRF_DODEFAULT | OS.CDRF_NOTIFYPOSTPAINT);
    } else {
      result = new LRESULT(OS.CDRF_NEWFONT | OS.CDRF_NOTIFYPOSTPAINT);
      if (hFont != (-1)) {
        OS.SelectObject(hDC, hFont);
      }
      if (OS.IsWindowEnabled(handle) && OS.IsWindowVisible(handle)) {
        if (clrTextBk != (-1)) {
          int bits = OS.GetWindowLong(handle, GWL_STYLE);
          if ((bits & OS.TVS_FULLROWSELECT) == 0) {
            if ((count != 0) && (hwndHeader != 0)) {
              RECT rect = new RECT();
              HDITEM hdItem = new HDITEM();
              hdItem.mask = OS.HDI_WIDTH;
              OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
              OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.left + hdItem.cxy, nmcd.bottom);
              if ((OS.COMCTL32_MAJOR < 6) || (!OS.IsAppThemed())) {
                RECT itemRect = new RECT();
                itemRect.left = item.handle;
                if (OS.SendMessage(handle, TVM_GETITEMRECT, 1, itemRect) != 0) {
                  rect.left = Math.min(itemRect.left, rect.right);
                }
              }
              if ((style & SWT.FULL_SELECTION) != 0) {
                if (!selected) {
                  fillBackground(hDC, clrTextBk, rect);
                }
              } else {
                fillBackground(hDC, clrTextBk, rect);
              }
            } else if ((style & SWT.FULL_SELECTION) != 0) {
              RECT rect = new RECT();
              OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
              if (!selected) {
                fillBackground(hDC, clrTextBk, rect);
              }
            }
          }
        }
        if (!selected) {
          nmcd.clrText = (clrText == (-1)) ? getForegroundPixel() : clrText;
          nmcd.clrTextBk = (clrTextBk == (-1)) ? getBackgroundPixel() : clrTextBk;
          OS.MoveMemory(lParam, nmcd, sizeof);
        }
      }
    }
    OS.SaveDC(hDC);
    if (clipRect != null) {
      int hRgn = OS.CreateRectRgn(clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
      OS.SelectClipRgn(hDC, hRgn);
      OS.DeleteObject(hRgn);
    }
    return result;
  }
}
