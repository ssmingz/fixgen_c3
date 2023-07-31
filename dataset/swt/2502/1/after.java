class PlaceHold {
  LRESULT CDDS_ITEMPOSTPAINT(NMTVCUSTOMDRAW nmcd, int wParam, int lParam) {
    if (ignoreCustomDraw) {
      return null;
    }
    if (nmcd.left == nmcd.right) {
      return new LRESULT(OS.CDRF_DODEFAULT);
    }
    int hDC = nmcd.hdc;
    OS.RestoreDC(hDC, -1);
    TreeItem item = getItem(nmcd);
    if (item == null) {
      return null;
    }
    if ((nmcd.left >= nmcd.right) || (nmcd.top >= nmcd.bottom)) {
      return null;
    }
    if (!OS.IsWindowVisible(handle)) {
      return null;
    }
    if (((((style & SWT.FULL_SELECTION) != 0) || (findImageControl() != null))
            || ignoreDrawSelection)
        || explorerTheme) {
      OS.SetBkMode(hDC, TRANSPARENT);
    }
    boolean selected = isItemSelected(nmcd);
    boolean hot = explorerTheme && ((nmcd.uItemState & OS.CDIS_HOT) != 0);
    if (OS.IsWindowEnabled(handle)) {
      if (explorerTheme) {
        int bits = OS.GetWindowLong(handle, GWL_STYLE);
        if ((bits & OS.TVS_TRACKSELECT) != 0) {
          if (((style & SWT.FULL_SELECTION) != 0) && (selected || hot)) {
            OS.SetTextColor(hDC, OS.GetSysColor(COLOR_WINDOWTEXT));
          } else {
            OS.SetTextColor(hDC, getForegroundPixel());
          }
        }
      }
    }
    int count = 0;
    int[] order = null;
    RECT clientRect = new RECT();
    OS.GetClientRect(scrolledHandle(), clientRect);
    if (hwndHeader != 0) {
      OS.MapWindowPoints(hwndParent, handle, clientRect, 2);
      count = ((int) (OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0)));
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
    int x = 0;
    Point size = null;
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
        if ((style & SWT.FULL_SELECTION) != 0) {
          boolean clear =
              ((!explorerTheme) && (!ignoreDrawSelection)) && (findImageControl() == null);
          if ((clear || (selected && (!ignoreDrawSelection))) || (hot && (!ignoreDrawHot))) {
            boolean draw = true;
            RECT pClipRect = new RECT();
            OS.SetRect(pClipRect, width, nmcd.top, nmcd.right, nmcd.bottom);
            if (explorerTheme) {
              if (hooks(EraseItem)) {
                RECT itemRect = item.getBounds(index, true, true, false, false, true, hDC);
                itemRect.left -= EXPLORER_EXTRA;
                itemRect.right += EXPLORER_EXTRA + 1;
                pClipRect.left = itemRect.left;
                pClipRect.right = itemRect.right;
                if ((count > 0) && (hwndHeader != 0)) {
                  HDITEM hdItem = new HDITEM();
                  hdItem.mask = OS.HDI_WIDTH;
                  OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
                  pClipRect.right = Math.min(pClipRect.right, nmcd.left + hdItem.cxy);
                }
              }
              RECT pRect = new RECT();
              OS.SetRect(pRect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
              if ((count > 0) && (hwndHeader != 0)) {
                int totalWidth = 0;
                HDITEM hdItem = new HDITEM();
                hdItem.mask = OS.HDI_WIDTH;
                for (int j = 0; j < count; j++) {
                  OS.SendMessage(hwndHeader, HDM_GETITEM, j, hdItem);
                  totalWidth += hdItem.cxy;
                }
                if (totalWidth > (clientRect.right - clientRect.left)) {
                  pRect.left = 0;
                  pRect.right = totalWidth;
                } else {
                  pRect.left = clientRect.left;
                  pRect.right = clientRect.right;
                }
              }
              draw = false;
              int hTheme = OS.OpenThemeData(handle, TREEVIEW);
              int iStateId = (selected) ? OS.TREIS_SELECTED : OS.TREIS_HOT;
              if (((OS.GetFocus() != handle) && selected) && (!hot)) {
                iStateId = OS.TREIS_SELECTEDNOTFOCUS;
              }
              OS.DrawThemeBackground(hTheme, hDC, TVP_TREEITEM, iStateId, pRect, pClipRect);
              OS.CloseThemeData(hTheme);
            }
            if (draw) {
              fillBackground(hDC, OS.GetBkColor(hDC), pClipRect);
            }
          }
        } else if (explorerTheme && hooks(EraseItem)) {
          if ((selected && (!ignoreDrawSelection)) || (hot && (!ignoreDrawHot))) {
            RECT pRect = item.getBounds(index, true, true, false, false, false, hDC);
            RECT pClipRect = item.getBounds(index, true, true, false, false, true, hDC);
            pRect.left -= EXPLORER_EXTRA;
            pRect.right += EXPLORER_EXTRA;
            pClipRect.left -= EXPLORER_EXTRA;
            pClipRect.right += EXPLORER_EXTRA;
            int hTheme = OS.OpenThemeData(handle, TREEVIEW);
            int iStateId = (selected) ? OS.TREIS_SELECTED : OS.TREIS_HOT;
            if (((OS.GetFocus() != handle) && selected) && (!hot)) {
              iStateId = OS.TREIS_SELECTEDNOTFOCUS;
            }
            OS.DrawThemeBackground(hTheme, hDC, TVP_TREEITEM, iStateId, pRect, pClipRect);
            OS.CloseThemeData(hTheme);
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
            if (explorerTheme) {
              if (OS.IsWindowEnabled(handle) && (!hooks(EraseItem))) {
                Image image = null;
                if (index == 0) {
                  image = item.image;
                } else {
                  Image[] images = item.images;
                  if (images != null) {
                    image = images[index];
                  }
                }
                if (image != null) {
                  Rectangle bounds = image.getBounds();
                  if (size == null) {
                    size = getImageSize();
                  }
                  if (!ignoreDrawForeground) {
                    GCData data = new GCData();
                    data.device = display;
                    GC gc = GC.win32_new(hDC, data);
                    RECT iconRect = item.getBounds(index, false, true, false, false, true, hDC);
                    gc.setClipping(
                        iconRect.left,
                        iconRect.top,
                        iconRect.right - iconRect.left,
                        iconRect.bottom - iconRect.top);
                    gc.drawImage(
                        image,
                        0,
                        0,
                        bounds.width,
                        bounds.height,
                        iconRect.left,
                        iconRect.top,
                        size.x,
                        size.y);
                    OS.SelectClipRgn(hDC, 0);
                    gc.dispose();
                  }
                }
              }
            } else {
              drawItem = drawText = drawBackground = true;
              rect = item.getBounds(index, true, false, false, false, true, hDC);
              if (linesVisible) {
                rect.right++;
                rect.bottom++;
              }
            }
          }
          if ((selected && (!ignoreDrawSelection)) && (!ignoreDrawBackground)) {
            if (!explorerTheme) {
              fillBackground(hDC, OS.GetBkColor(hDC), rect);
            }
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
          ignoreDrawForeground =
              ignoreDrawBackground = ignoreDrawSelection = ignoreDrawFocus = ignoreDrawHot = false;
          OS.SetRect(rect, x, nmcd.top, x + width, nmcd.bottom);
          backgroundRect = rect;
        }
        int clrText = -1;
        int clrTextBk = -1;
        int hFont = item.fontHandle(index);
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
            if ((clrTextBk == (-1)) && (index == sortIndex)) {
              clrTextBk = clrSortBk;
            }
          }
        } else if ((clrTextBk == (-1)) && (index == sortIndex)) {
          drawBackground = true;
          clrTextBk = clrSortBk;
        }
        if (explorerTheme) {
          if (selected || ((nmcd.uItemState & OS.CDIS_HOT) != 0)) {
            if ((style & SWT.FULL_SELECTION) != 0) {
              drawBackground = false;
            } else if (i == 0) {
              drawBackground = false;
              if (!hooks(EraseItem)) {
                drawText = false;
              }
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
              if (event.height > getItemHeight()) {
                setItemHeight(event.height);
              }
            }
            if (hooks(EraseItem)) {
              RECT cellRect = item.getBounds(index, true, true, true, true, true, hDC);
              int nSavedDC = OS.SaveDC(hDC);
              GCData data = new GCData();
              data.device = display;
              data.foreground = OS.GetTextColor(hDC);
              data.background = OS.GetBkColor(hDC);
              if ((!selected) || ((style & SWT.FULL_SELECTION) == 0)) {
                if (clrText != (-1)) {
                  data.foreground = clrText;
                }
                if (clrTextBk != (-1)) {
                  data.background = clrTextBk;
                }
              }
              data.hFont = hFont;
              data.uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
              GC gc = GC.win32_new(hDC, data);
              Event event = new Event();
              event.item = item;
              event.index = index;
              event.gc = gc;
              event.detail |= SWT.FOREGROUND;
              if (clrTextBk != (-1)) {
                event.detail |= SWT.BACKGROUND;
              }
              if ((style & SWT.FULL_SELECTION) != 0) {
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
                break;
              }
              if (event.doit) {
                ignoreDrawForeground = (event.detail & SWT.FOREGROUND) == 0;
                ignoreDrawBackground = (event.detail & SWT.BACKGROUND) == 0;
                if ((style & SWT.FULL_SELECTION) != 0) {
                  ignoreDrawSelection = (event.detail & SWT.SELECTED) == 0;
                  ignoreDrawFocus = (event.detail & SWT.FOCUSED) == 0;
                  ignoreDrawHot = (event.detail & SWT.HOT) == 0;
                }
              } else {
                ignoreDrawForeground =
                    ignoreDrawBackground =
                        ignoreDrawSelection = ignoreDrawFocus = ignoreDrawHot = true;
              }
              if (selected && ignoreDrawSelection) {
                ignoreDrawHot = true;
              }
              if ((style & SWT.FULL_SELECTION) != 0) {
                if (ignoreDrawSelection) {
                  ignoreFullSelection = true;
                }
                if ((!ignoreDrawSelection) || (!ignoreDrawHot)) {
                  if ((!selected) && (!hot)) {
                    selectionForeground = OS.GetSysColor(COLOR_HIGHLIGHTTEXT);
                  } else if (!explorerTheme) {
                    drawBackground = true;
                    ignoreDrawBackground = false;
                    if ((handle == OS.GetFocus()) && OS.IsWindowEnabled(handle)) {
                      clrTextBk = OS.GetSysColor(COLOR_HIGHLIGHT);
                    } else {
                      clrTextBk = OS.GetSysColor(COLOR_3DFACE);
                    }
                    if ((!ignoreFullSelection) && (index == (count - 1))) {
                      RECT selectionRect = new RECT();
                      OS.SetRect(
                          selectionRect,
                          backgroundRect.left,
                          backgroundRect.top,
                          nmcd.right,
                          backgroundRect.bottom);
                      backgroundRect = selectionRect;
                    }
                  } else {
                    RECT pRect = new RECT();
                    OS.SetRect(pRect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                    if ((count > 0) && (hwndHeader != 0)) {
                      int totalWidth = 0;
                      HDITEM hdItem = new HDITEM();
                      hdItem.mask = OS.HDI_WIDTH;
                      for (int j = 0; j < count; j++) {
                        OS.SendMessage(hwndHeader, HDM_GETITEM, j, hdItem);
                        totalWidth += hdItem.cxy;
                      }
                      if (totalWidth > (clientRect.right - clientRect.left)) {
                        pRect.left = 0;
                        pRect.right = totalWidth;
                      } else {
                        pRect.left = clientRect.left;
                        pRect.right = clientRect.right;
                      }
                      if (index == (count - 1)) {
                        RECT selectionRect = new RECT();
                        OS.SetRect(
                            selectionRect,
                            backgroundRect.left,
                            backgroundRect.top,
                            pRect.right,
                            backgroundRect.bottom);
                        backgroundRect = selectionRect;
                      }
                    }
                    int hTheme = OS.OpenThemeData(handle, TREEVIEW);
                    int iStateId = (selected) ? OS.TREIS_SELECTED : OS.TREIS_HOT;
                    if (((OS.GetFocus() != handle) && selected) && (!hot)) {
                      iStateId = OS.TREIS_SELECTEDNOTFOCUS;
                    }
                    OS.DrawThemeBackground(
                        hTheme, hDC, TVP_TREEITEM, iStateId, pRect, backgroundRect);
                    OS.CloseThemeData(hTheme);
                  }
                } else if (selected) {
                  selectionForeground = newTextClr;
                  if (!explorerTheme) {
                    if ((clrTextBk == (-1)) && OS.IsWindowEnabled(handle)) {
                      Control control = findBackgroundControl();
                      if (control == null) {
                        control = this;
                      }
                      clrTextBk = control.getBackgroundPixel();
                    }
                  }
                }
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
                } else if (drawBackground) {
                  fillImageBackground(hDC, control, rect);
                }
              }
            }
          }
          rect.left += INSET - 1;
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
            int offset = (i != 0) ? INSET : INSET + 2;
            if (image != null) {
              Rectangle bounds = image.getBounds();
              if (size == null) {
                size = getImageSize();
              }
              if (!ignoreDrawForeground) {
                int y1 = rect.top;
                int x1 = Math.max(rect.left, (rect.left - inset) + 1);
                GCData data = new GCData();
                data.device = display;
                GC gc = GC.win32_new(hDC, data);
                gc.setClipping(x1, rect.top, rect.right - x1, rect.bottom - rect.top);
                gc.drawImage(image, 0, 0, bounds.width, bounds.height, x1, y1, size.x, size.y);
                OS.SelectClipRgn(hDC, 0);
                gc.dispose();
              }
              OS.SetRect(
                  rect, (rect.left + size.x) + offset, rect.top, rect.right - inset, rect.bottom);
            } else if (i == 0) {
              if (OS.SendMessage(handle, TVM_GETIMAGELIST, TVSIL_NORMAL, 0) != 0) {
                if (size == null) {
                  size = getImageSize();
                }
                rect.left = Math.min((rect.left + size.x) + offset, rect.right);
              }
            } else {
              OS.SetRect(rect, rect.left + offset, rect.top, rect.right - inset, rect.bottom);
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
                if (!ignoreDrawForeground) {
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
          if (selected && ((style & SWT.FULL_SELECTION) != 0)) {
            if (selectionForeground != (-1)) {
              data.foreground = selectionForeground;
            }
          } else {
            if (clrText != (-1)) {
              data.foreground = clrText;
            }
            if (clrTextBk != (-1)) {
              data.background = clrTextBk;
            }
          }
          data.uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
          GC gc = GC.win32_new(hDC, data);
          Event event = new Event();
          event.item = item;
          event.index = index;
          event.gc = gc;
          event.detail |= SWT.FOREGROUND;
          if (clrTextBk != (-1)) {
            event.detail |= SWT.BACKGROUND;
          }
          if (hot) {
            event.detail |= SWT.HOT;
          }
          if (selected && ((i == 0) || ((style & SWT.FULL_SELECTION) != 0))) {
            event.detail |= SWT.SELECTED;
          }
          if (!explorerTheme) {
            if (OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0) == nmcd.dwItemSpec) {
              if ((i == 0) || ((style & SWT.FULL_SELECTION) != 0)) {
                if (handle == OS.GetFocus()) {
                  int uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
                  if ((uiState & OS.UISF_HIDEFOCUS) == 0) {
                    event.detail |= SWT.FOCUSED;
                  }
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
    if (!explorerTheme) {
      if (handle == OS.GetFocus()) {
        int uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
        if ((uiState & OS.UISF_HIDEFOCUS) == 0) {
          int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
          if (hItem == item.handle) {
            if ((!ignoreDrawFocus) && (findImageControl() != null)) {
              if ((style & SWT.FULL_SELECTION) != 0) {
                RECT focusRect = new RECT();
                OS.SetRect(focusRect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                if ((count > 0) && (hwndHeader != 0)) {
                  int width = 0;
                  HDITEM hdItem = new HDITEM();
                  hdItem.mask = OS.HDI_WIDTH;
                  for (int j = 0; j < count; j++) {
                    OS.SendMessage(hwndHeader, HDM_GETITEM, j, hdItem);
                    width += hdItem.cxy;
                  }
                  focusRect.left = 0;
                  RECT rect = new RECT();
                  OS.GetClientRect(handle, rect);
                  focusRect.right = Math.max(width, rect.right - OS.GetSystemMetrics(SM_CXVSCROLL));
                }
                OS.DrawFocusRect(hDC, focusRect);
              } else {
                int index = ((int) (OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, 0, 0)));
                RECT focusRect = item.getBounds(index, true, false, false, false, false, hDC);
                RECT clipRect = item.getBounds(index, true, false, false, false, true, hDC);
                OS.IntersectClipRect(
                    hDC, clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
                OS.DrawFocusRect(hDC, focusRect);
                OS.SelectClipRgn(hDC, 0);
              }
            }
          }
        }
      }
    }
    return new LRESULT(OS.CDRF_DODEFAULT);
  }
}
