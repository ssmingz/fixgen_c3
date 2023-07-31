class PlaceHold {
  LRESULT CDDS_ITEMPOSTPAINT(int wParam, int lParam) {
    NMTVCUSTOMDRAW nmcd = new NMTVCUSTOMDRAW();
    OS.MoveMemory(nmcd, lParam, sizeof);
    int hDC = nmcd.hdc;
    OS.RestoreDC(hDC, -1);
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
    if ((nmcd.left >= nmcd.right) || (nmcd.top >= nmcd.bottom)) {
      return null;
    }
    if (!OS.IsWindowVisible(handle)) {
      return null;
    }
    if ((findImageControl() != null) || ignoreDrawSelection) {
      OS.SetBkMode(hDC, TRANSPARENT);
    }
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
      } else if (OS.GetTextColor(hDC) == OS.GetSysColor(COLOR_HIGHLIGHTTEXT)) {
        if (OS.GetBkColor(hDC) == OS.GetSysColor(COLOR_HIGHLIGHT)) {
          selected = true;
        }
      }
    }
    int count = 0;
    int[] order = null;
    RECT clientRect = new RECT();
    RECT focusRect = null;
    OS.GetClientRect(scrolledHandle(), clientRect);
    if (hwndHeader != 0) {
      OS.MapWindowPoints(hwndParent, handle, clientRect, 2);
      count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
      if (count != 0) {
        order = new int[count];
        OS.SendMessage(hwndHeader, HDM_GETORDERARRAY, count, order);
      }
    }
    int sortIndex = -1;
    int clrSortBk = -1;
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      if ((sortColumn != null) && (sortDirection != SWT.NONE)) {
        if (findImageControl() == null) {
          sortIndex = indexOf(sortColumn);
          clrSortBk = getSortColumnPixel();
        }
      }
    }
    Point size = null;
    int x = 0;
    int gridWidth = (linesVisible) ? GRID_WIDTH : 0;
    for (int i = 0; i < Math.max(1, count); i++) {
      int index = (order == null) ? i : order[i];
      int width = nmcd.right - nmcd.left;
      if ((count > 0) && (hwndHeader != 0)) {
        HDITEM hdItem = new HDITEM();
        hdItem.mask = OS.HDI_WIDTH;
        OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
        width = hdItem.cxy;
      }
      if (i == 0) {
        RECT rect = new RECT();
        if ((style & SWT.FULL_SELECTION) != 0) {
          OS.SetRect(rect, width, nmcd.top, nmcd.right, nmcd.bottom);
          if (((selected || (findImageControl() == null)) && (!ignoreDrawSelection))
              && (!ignoreDrawBackground)) {
            fillBackground(hDC, OS.GetBkColor(hDC), rect);
          }
        }
      }
      if ((x + width) > clientRect.left) {
        RECT rect = new RECT();
        RECT backgroundRect = null;
        boolean drawItem = true;
        boolean drawText = true;
        boolean drawImage = true;
        boolean drawBackground = false;
        if (i == 0) {
          drawItem = drawImage = drawText = false;
          if (findImageControl() != null) {
            drawItem = drawText = drawBackground = true;
            rect = item.getBounds(index, true, false, false, false, true, hDC);
            if (linesVisible) {
              rect.right++;
              rect.bottom++;
            }
          }
          if ((selected && (!ignoreDrawSelection)) && (!ignoreDrawBackground)) {
            fillBackground(hDC, OS.GetBkColor(hDC), rect);
            drawBackground = false;
          }
          backgroundRect = rect;
          if (hooks(EraseItem)) {
            drawItem = drawText = drawImage = true;
            rect = item.getBounds(index, true, true, false, false, true, hDC);
            if ((style & SWT.FULL_SELECTION) != 0) {
              backgroundRect = rect;
            } else {
              backgroundRect = item.getBounds(index, true, false, false, false, true, hDC);
            }
          }
        } else {
          selectionForeground = -1;
          ignoreDraw = ignoreDrawSelection = ignoreDrawBackground = false;
          OS.SetRect(rect, x, nmcd.top, x + width, nmcd.bottom - gridWidth);
          backgroundRect = rect;
        }
        int clrText = -1;
        int clrTextBk = -1;
        int hFont = (item.cellFont != null) ? item.cellFont[index] : -1;
        if (hFont == (-1)) {
          hFont = item.font;
        }
        if (selectionForeground != (-1)) {
          clrText = selectionForeground;
        }
        if (OS.IsWindowEnabled(handle)) {
          boolean drawForeground = false;
          if (selected) {
            if ((i != 0) && ((style & SWT.FULL_SELECTION) == 0)) {
              OS.SetTextColor(hDC, getForegroundPixel());
              OS.SetBkColor(hDC, getBackgroundPixel());
              drawForeground = drawBackground = true;
            }
          } else {
            drawForeground = drawBackground = true;
          }
          if (drawForeground) {
            clrText = (item.cellForeground != null) ? item.cellForeground[index] : -1;
            if (clrText == (-1)) {
              clrText = item.foreground;
            }
          }
          if (drawBackground) {
            clrTextBk = (item.cellBackground != null) ? item.cellBackground[index] : -1;
            if (clrTextBk == (-1)) {
              clrTextBk = item.background;
            }
            if (index == sortIndex) {
              clrTextBk = clrSortBk;
            }
          }
        }
        if (drawItem) {
          if (i != 0) {
            if (hooks(MeasureItem)) {
              RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
              int nSavedDC = OS.SaveDC(hDC);
              GCData data = new GCData();
              data.device = display;
              data.hFont = hFont;
              GC gc = GC.win32_new(hDC, data);
              Event event = new Event();
              event.item = item;
              event.index = index;
              event.gc = gc;
              event.x = itemRect.left;
              event.y = itemRect.top;
              event.width = itemRect.right - itemRect.left;
              event.height = itemRect.bottom - itemRect.top;
              sendEvent(MeasureItem, event);
              event.gc = null;
              gc.dispose();
              OS.RestoreDC(hDC, nSavedDC);
              if (isDisposed() || item.isDisposed()) {
                break;
              }
              if (!ignoreItemHeight) {
                if (event.height > getItemHeight()) {
                  setItemHeight(event.height);
                }
                ignoreItemHeight = true;
              }
            }
            if (hooks(EraseItem)) {
              RECT cellRect = item.getBounds(index, true, true, true, true, true, hDC);
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
              data.hFont = hFont;
              GC gc = GC.win32_new(hDC, data);
              Event event = new Event();
              event.item = item;
              event.index = index;
              event.gc = gc;
              event.detail |= SWT.FOREGROUND;
              if (clrTextBk != (-1)) {
                event.detail |= SWT.BACKGROUND;
              }
              if (selected && ((style & SWT.FULL_SELECTION) != 0)) {
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
                break;
              }
              if (event.doit) {
                ignoreDraw = (event.detail & SWT.FOREGROUND) == 0;
                ignoreDrawSelection = (event.detail & SWT.SELECTED) == 0;
                ignoreDrawBackground = (event.detail & SWT.BACKGROUND) == 0;
              } else {
                ignoreDraw = ignoreDrawSelection = ignoreDrawBackground = true;
              }
              if (!ignoreDrawSelection) {
                if (!selected) {
                  selectionForeground = OS.GetSysColor(COLOR_HIGHLIGHTTEXT);
                }
              } else if (selected) {
                selectionForeground = newTextClr;
              }
            }
            if (selectionForeground != (-1)) {
              clrText = selectionForeground;
            }
          }
          if (!ignoreDrawBackground) {
            if (clrTextBk != (-1)) {
              if (drawBackground) {
                fillBackground(hDC, clrTextBk, backgroundRect);
              }
            } else {
              Control control = findImageControl();
              if (control != null) {
                if (i == 0) {
                  int right = Math.min(rect.right, width);
                  OS.SetRect(rect, rect.left, rect.top, right, rect.bottom);
                  if (drawBackground) {
                    fillImageBackground(hDC, control, rect);
                  }
                  if (!ignoreDrawSelection) {
                    if (handle == OS.GetFocus()) {
                      int uiState = OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0);
                      if ((uiState & OS.UISF_HIDEFOCUS) == 0) {
                        int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
                        if (hItem == item.handle) {
                          if ((style & SWT.FULL_SELECTION) != 0) {
                            focusRect = new RECT();
                            OS.SetRect(focusRect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                          } else {
                            focusRect = item.getBounds(index, true, false, false, false, true, hDC);
                          }
                        }
                      }
                    }
                  }
                } else if (drawBackground) {
                  fillImageBackground(hDC, control, rect);
                }
              }
            }
          }
          if (drawImage) {
            Image image = null;
            if (index == 0) {
              image = item.image;
            } else {
              Image[] images = item.images;
              if (images != null) {
                image = images[index];
              }
            }
            int inset = (i != 0) ? INSET : 0;
            if (image != null) {
              if (index == 0) {
                rect.left = Math.min(rect.right, rect.left + 2);
              }
              Rectangle bounds = image.getBounds();
              if (size == null) {
                size = getImageSize();
              }
              int y = rect.top;
              if (!ignoreDraw) {
                GCData data = new GCData();
                data.device = display;
                GC gc = GC.win32_new(hDC, data);
                gc.drawImage(
                    image, 0, 0, bounds.width, bounds.height, rect.left, y, size.x, size.y);
                gc.dispose();
              }
              OS.SetRect(
                  rect, (rect.left + size.x) + INSET, rect.top, rect.right - inset, rect.bottom);
            } else {
              OS.SetRect(rect, rect.left + INSET, rect.top, rect.right - inset, rect.bottom);
              if ((i == 0) && (OS.SendMessage(handle, TVM_GETIMAGELIST, TVSIL_NORMAL, 0) != 0)) {
                if (size == null) {
                  size = getImageSize();
                }
                rect.left = Math.min(rect.left + size.x, rect.right);
              }
            }
          }
          if (drawText) {
            if (rect.left < rect.right) {
              String string = null;
              if (index == 0) {
                string = item.text;
              } else {
                String[] strings = item.strings;
                if (strings != null) {
                  string = strings[index];
                }
              }
              if (string != null) {
                if (hFont != (-1)) {
                  hFont = OS.SelectObject(hDC, hFont);
                }
                if (clrText != (-1)) {
                  clrText = OS.SetTextColor(hDC, clrText);
                }
                if (clrTextBk != (-1)) {
                  clrTextBk = OS.SetBkColor(hDC, clrTextBk);
                }
                int flags = (OS.DT_NOPREFIX | OS.DT_SINGLELINE) | OS.DT_VCENTER;
                if (i != 0) {
                  flags |= OS.DT_ENDELLIPSIS;
                }
                TreeColumn column = (columns != null) ? columns[index] : null;
                if (column != null) {
                  if ((column.style & SWT.CENTER) != 0) {
                    flags |= OS.DT_CENTER;
                  }
                  if ((column.style & SWT.RIGHT) != 0) {
                    flags |= OS.DT_RIGHT;
                  }
                }
                TCHAR buffer = new TCHAR(getCodePage(), string, false);
                if (!ignoreDraw) {
                  OS.DrawText(hDC, buffer, buffer.length(), rect, flags);
                }
                OS.DrawText(hDC, buffer, buffer.length(), rect, flags | OS.DT_CALCRECT);
                if (hFont != (-1)) {
                  hFont = OS.SelectObject(hDC, hFont);
                }
                if (clrText != (-1)) {
                  clrText = OS.SetTextColor(hDC, clrText);
                }
                if (clrTextBk != (-1)) {
                  clrTextBk = OS.SetBkColor(hDC, clrTextBk);
                }
              }
            }
          }
        }
        if (selectionForeground != (-1)) {
          clrText = selectionForeground;
        }
        if (hooks(PaintItem)) {
          RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
          int nSavedDC = OS.SaveDC(hDC);
          GCData data = new GCData();
          data.device = display;
          data.hFont = hFont;
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
          OS.SelectObject(hDC, data.hPen);
          OS.SelectObject(hDC, data.hBrush);
          GC gc = GC.win32_new(hDC, data);
          Event event = new Event();
          event.item = item;
          event.index = index;
          event.gc = gc;
          event.detail |= SWT.FOREGROUND;
          if (clrTextBk != (-1)) {
            event.detail |= SWT.BACKGROUND;
          }
          if (selected && ((i == 0) || ((style & SWT.FULL_SELECTION) != 0))) {
            event.detail |= SWT.SELECTED;
          }
          if ((nmcd.uItemState & OS.CDIS_FOCUS) != 0) {
            if ((i == 0) || ((style & SWT.FULL_SELECTION) != 0)) {
              if (handle == OS.GetFocus()) {
                int uiState = OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0);
                if ((uiState & OS.UISF_HIDEFOCUS) == 0) {
                  event.detail |= SWT.FOCUSED;
                }
              }
            }
          }
          event.x = itemRect.left;
          event.y = itemRect.top;
          event.width = itemRect.right - itemRect.left;
          event.height = itemRect.bottom - itemRect.top;
          RECT cellRect = item.getBounds(index, true, true, true, true, true, hDC);
          int cellWidth = cellRect.right - cellRect.left;
          int cellHeight = cellRect.bottom - cellRect.top;
          gc.setClipping(cellRect.left, cellRect.top, cellWidth, cellHeight);
          sendEvent(PaintItem, event);
          event.gc = null;
          gc.dispose();
          OS.RestoreDC(hDC, nSavedDC);
          if (isDisposed() || item.isDisposed()) {
            break;
          }
        }
      }
      x += width;
      if (x > clientRect.right) {
        break;
      }
    }
    if (linesVisible) {
      if ((style & SWT.FULL_SELECTION) != 0) {
        if (hwndHeader != 0) {
          if (OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0) != 0) {
            HDITEM hdItem = new HDITEM();
            hdItem.mask = OS.HDI_WIDTH;
            OS.SendMessage(hwndHeader, HDM_GETITEM, 0, hdItem);
            RECT rect = new RECT();
            OS.SetRect(rect, nmcd.left + hdItem.cxy, nmcd.top, nmcd.right, nmcd.bottom);
            OS.DrawEdge(hDC, rect, BDR_SUNKENINNER, BF_BOTTOM);
          }
        }
      }
      RECT rect = new RECT();
      OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
      OS.DrawEdge(hDC, rect, BDR_SUNKENINNER, BF_BOTTOM);
    }
    if (focusRect != null) {
      OS.DrawFocusRect(hDC, focusRect);
    }
    return new LRESULT(OS.CDRF_DODEFAULT);
  }
}
