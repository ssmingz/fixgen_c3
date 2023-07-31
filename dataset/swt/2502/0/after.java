class PlaceHold {
  LRESULT CDDS_ITEMPREPAINT(NMTVCUSTOMDRAW nmcd, int wParam, int lParam) {
    TreeItem item = getItem(nmcd);
    if (item == null) {
      return null;
    }
    int hDC = nmcd.hdc;
    int index =
        (hwndHeader != 0) ? ((int) (OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, 0, 0))) : 0;
    int hFont = item.fontHandle(index);
    if (hFont != (-1)) {
      OS.SelectObject(hDC, hFont);
    }
    if (ignoreCustomDraw || (nmcd.left == nmcd.right)) {
      return new LRESULT(hFont == (-1) ? OS.CDRF_DODEFAULT : OS.CDRF_NEWFONT);
    }
    int count = 0;
    RECT clipRect = null;
    if (hwndHeader != 0) {
      count = ((int) (OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0)));
      if (count != 0) {
        boolean clip = !printClient;
        if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
          clip = true;
        }
        if (clip) {
          clipRect = new RECT();
          HDITEM hdItem = new HDITEM();
          hdItem.mask = OS.HDI_WIDTH;
          OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
          OS.SetRect(clipRect, nmcd.left, nmcd.top, nmcd.left + hdItem.cxy, nmcd.bottom);
        }
      }
    }
    int clrText = -1;
    int clrTextBk = -1;
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
            clrSortBk = getSortColumnPixel();
            if (clrTextBk == (-1)) {
              clrTextBk = clrSortBk;
            }
          }
        }
      }
    }
    boolean selected = isItemSelected(nmcd);
    boolean hot = explorerTheme && ((nmcd.uItemState & OS.CDIS_HOT) != 0);
    if ((OS.IsWindowVisible(handle) && (nmcd.left < nmcd.right)) && (nmcd.top < nmcd.bottom)) {
      if (hFont != (-1)) {
        OS.SelectObject(hDC, hFont);
      }
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
      ignoreDrawForeground =
          ignoreDrawBackground =
              ignoreDrawSelection = ignoreDrawFocus = ignoreDrawHot = ignoreFullSelection = false;
      if (hooks(EraseItem)) {
        RECT rect = new RECT();
        OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
        if (OS.IsWindowEnabled(handle) || (findImageControl() != null)) {
          drawBackground(hDC, rect);
        } else {
          fillBackground(hDC, OS.GetBkColor(hDC), rect);
        }
        RECT cellRect = item.getBounds(index, true, true, true, true, true, hDC);
        if (clrSortBk != (-1)) {
          RECT fullRect = item.getBounds(index, true, true, true, true, true, hDC);
          drawBackground(hDC, fullRect, clrSortBk);
        }
        int nSavedDC = OS.SaveDC(hDC);
        GCData data = new GCData();
        data.device = display;
        if (selected && explorerTheme) {
          data.foreground = OS.GetSysColor(COLOR_WINDOWTEXT);
        } else {
          data.foreground = OS.GetTextColor(hDC);
        }
        data.background = OS.GetBkColor(hDC);
        if (!selected) {
          if (clrText != (-1)) {
            data.foreground = clrText;
          }
          if (clrTextBk != (-1)) {
            data.background = clrTextBk;
          }
        }
        data.uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
        if (hFont != (-1)) {
          data.hFont = hFont;
        }
        GC gc = GC.win32_new(hDC, data);
        Event event = new Event();
        event.index = index;
        event.item = item;
        event.gc = gc;
        event.detail |= SWT.FOREGROUND;
        if (clrTextBk != (-1)) {
          event.detail |= SWT.BACKGROUND;
        }
        if (hot) {
          event.detail |= SWT.HOT;
        }
        if (selected) {
          event.detail |= SWT.SELECTED;
        }
        if (!explorerTheme) {
          if (OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0) == nmcd.dwItemSpec) {
            if (handle == OS.GetFocus()) {
              int uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
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
        int newTextClr = data.foreground;
        gc.dispose();
        OS.RestoreDC(hDC, nSavedDC);
        if (isDisposed() || item.isDisposed()) {
          return null;
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
        if (selected && ignoreDrawSelection) {
          ignoreDrawHot = true;
        }
        if ((!ignoreDrawBackground) && (clrTextBk != (-1))) {
          boolean draw = (!selected) && (!hot);
          if ((!explorerTheme) && selected) {
            draw = !ignoreDrawSelection;
          }
          if (draw) {
            if (count == 0) {
              if ((style & SWT.FULL_SELECTION) != 0) {
                fillBackground(hDC, clrTextBk, rect);
              } else {
                RECT textRect = item.getBounds(index, true, false, true, false, true, hDC);
                fillBackground(hDC, clrTextBk, textRect);
              }
            } else {
              fillBackground(hDC, clrTextBk, cellRect);
            }
          }
        }
        if (ignoreDrawSelection) {
          ignoreFullSelection = true;
        }
        if ((!ignoreDrawSelection) || (!ignoreDrawHot)) {
          if ((!selected) && (!hot)) {
            selectionForeground = clrText = OS.GetSysColor(COLOR_HIGHLIGHTTEXT);
          }
          if (!explorerTheme) {
            if ((style & SWT.FULL_SELECTION) != 0) {
              if (((style & SWT.FULL_SELECTION) != 0) && (count == 0)) {
                fillBackground(hDC, OS.GetBkColor(hDC), rect);
              } else {
                fillBackground(hDC, OS.GetBkColor(hDC), cellRect);
              }
            } else {
              RECT textRect = item.getBounds(index, true, false, false, false, true, hDC);
              fillBackground(hDC, OS.GetBkColor(hDC), textRect);
            }
          }
        } else {
          if (selected || hot) {
            selectionForeground = clrText = newTextClr;
            ignoreDrawSelection = ignoreDrawHot = true;
          }
          if (explorerTheme) {
            nmcd.uItemState |= OS.CDIS_DISABLED;
            int newColor = (clrText == (-1)) ? getForegroundPixel() : clrText;
            if (nmcd.clrText == newColor) {
              nmcd.clrText |= 0x20000000;
              if (nmcd.clrText == newColor) {
                nmcd.clrText &= ~0x20000000;
              }
            } else {
              nmcd.clrText = newColor;
            }
            OS.MoveMemory(lParam, nmcd, sizeof);
          }
        }
        if (explorerTheme) {
          if (selected || (hot && ignoreDrawHot)) {
            nmcd.uItemState &= ~OS.CDIS_HOT;
          }
          OS.MoveMemory(lParam, nmcd, sizeof);
        }
        RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
        OS.SaveDC(hDC);
        OS.SelectClipRgn(hDC, 0);
        if (explorerTheme) {
          itemRect.left -= EXPLORER_EXTRA;
          itemRect.right += EXPLORER_EXTRA;
        }
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
      if ((style & SWT.FULL_SELECTION) != 0) {
        int bits = OS.GetWindowLong(handle, GWL_STYLE);
        if ((bits & OS.TVS_FULLROWSELECT) == 0) {
          RECT rect = new RECT();
          OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
          if (selected) {
            fillBackground(hDC, OS.GetBkColor(hDC), rect);
          } else if (OS.IsWindowEnabled(handle)) {
            drawBackground(hDC, rect);
          }
          nmcd.uItemState &= ~OS.CDIS_FOCUS;
          OS.MoveMemory(lParam, nmcd, sizeof);
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
                if (OS.TreeView_GetItemRect(handle, item.handle, itemRect, true)) {
                  rect.left = Math.min(itemRect.left, rect.right);
                }
              }
              if ((style & SWT.FULL_SELECTION) != 0) {
                if (!selected) {
                  fillBackground(hDC, clrTextBk, rect);
                }
              } else if (explorerTheme) {
                if ((!selected) && (!hot)) {
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
    if (OS.IsWindowEnabled(handle)) {
      if (explorerTheme) {
        if (findImageControl() != null) {
          if ((!selected) && ((nmcd.uItemState & (OS.CDIS_HOT | OS.CDIS_SELECTED)) == 0)) {
            nmcd.uItemState |= OS.CDIS_DISABLED;
            int newColor = (clrText == (-1)) ? getForegroundPixel() : clrText;
            if (nmcd.clrText == newColor) {
              nmcd.clrText |= 0x20000000;
              if (nmcd.clrText == newColor) {
                nmcd.clrText &= ~0x20000000;
              }
            } else {
              nmcd.clrText = newColor;
            }
            OS.MoveMemory(lParam, nmcd, sizeof);
            if (clrTextBk != (-1)) {
              if ((style & SWT.FULL_SELECTION) != 0) {
                RECT rect = new RECT();
                if (count != 0) {
                  HDITEM hdItem = new HDITEM();
                  hdItem.mask = OS.HDI_WIDTH;
                  OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
                  OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.left + hdItem.cxy, nmcd.bottom);
                } else {
                  OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                }
                fillBackground(hDC, clrTextBk, rect);
              } else {
                RECT textRect = item.getBounds(index, true, false, true, false, true, hDC);
                fillBackground(hDC, clrTextBk, textRect);
              }
            }
          }
        }
      }
    } else if (clrSortBk != (-1)) {
      RECT rect = new RECT();
      HDITEM hdItem = new HDITEM();
      hdItem.mask = OS.HDI_WIDTH;
      OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
      OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.left + hdItem.cxy, nmcd.bottom);
      fillBackground(hDC, clrSortBk, rect);
    }
    OS.SaveDC(hDC);
    if (clipRect != null) {
      int hRgn = OS.CreateRectRgn(clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
      POINT lpPoint = new POINT();
      OS.GetWindowOrgEx(hDC, lpPoint);
      OS.OffsetRgn(hRgn, -lpPoint.x, -lpPoint.y);
      OS.SelectClipRgn(hDC, hRgn);
      OS.DeleteObject(hRgn);
    }
    return result;
  }
}
