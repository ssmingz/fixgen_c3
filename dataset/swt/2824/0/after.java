class PlaceHold {
  LRESULT CDDS_SUBITEMPREPAINT(NMLVCUSTOMDRAW nmcd, int wParam, int lParam) {
    int hDC = nmcd.hdc;
    if (((explorerTheme && (!ignoreCustomDraw)) && hooks(EraseItem)) && (nmcd.left != nmcd.right)) {
      OS.RestoreDC(hDC, -1);
    }
    TableItem item = _getItem(((int) (nmcd.dwItemSpec)));
    if (item == null) {
      return null;
    }
    int hFont = (item.cellFont != null) ? item.cellFont[nmcd.iSubItem] : -1;
    if (hFont == (-1)) {
      hFont = item.font;
    }
    if (hFont != (-1)) {
      OS.SelectObject(hDC, hFont);
    }
    if (ignoreCustomDraw || (nmcd.left == nmcd.right)) {
      return new LRESULT(hFont == (-1) ? OS.CDRF_DODEFAULT : OS.CDRF_NEWFONT);
    }
    int code = OS.CDRF_DODEFAULT;
    selectionForeground = -1;
    ignoreDrawForeground = ignoreDrawSelection = ignoreDrawFocus = ignoreDrawBackground = false;
    if (OS.IsWindowVisible(handle)) {
      if (hooks(MeasureItem)) {
        sendMeasureItemEvent(item, ((int) (nmcd.dwItemSpec)), nmcd.iSubItem, nmcd.hdc);
        if (isDisposed() || item.isDisposed()) {
          return null;
        }
      }
      if (hooks(EraseItem)) {
        sendEraseItemEvent(item, nmcd, lParam);
        if (isDisposed() || item.isDisposed()) {
          return null;
        }
        code |= OS.CDRF_NOTIFYPOSTPAINT;
      }
      if (ignoreDrawForeground || hooks(PaintItem)) {
        code |= OS.CDRF_NOTIFYPOSTPAINT;
      }
    }
    int clrText = (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
    if (clrText == (-1)) {
      clrText = item.foreground;
    }
    int clrTextBk = (item.cellBackground != null) ? item.cellBackground[nmcd.iSubItem] : -1;
    if (clrTextBk == (-1)) {
      clrTextBk = item.background;
    }
    if (selectionForeground != (-1)) {
      clrText = selectionForeground;
    }
    if (OS.IsWindowVisible(handle) && OS.IsWindowEnabled(handle)) {
      if (((!explorerTheme) && (!ignoreDrawSelection)) && ((style & SWT.FULL_SELECTION) != 0)) {
        int bits = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
        if ((bits & OS.LVS_EX_FULLROWSELECT) == 0) {
          LVITEM lvItem = new LVITEM();
          lvItem.mask = OS.LVIF_STATE;
          lvItem.stateMask = OS.LVIS_SELECTED;
          lvItem.iItem = ((int) (nmcd.dwItemSpec));
          int result = OS.SendMessage(handle, LVM_GETITEM, 0, lvItem);
          if ((result != 0) && ((lvItem.state & OS.LVIS_SELECTED) != 0)) {
            int clrSelection = -1;
            if (nmcd.iSubItem == 0) {
              if (OS.GetFocus() == handle) {
                clrSelection = OS.GetSysColor(COLOR_HIGHLIGHT);
              } else if ((style & SWT.HIDE_SELECTION) == 0) {
                clrSelection = OS.GetSysColor(COLOR_3DFACE);
              }
            } else if (OS.GetFocus() == handle) {
              clrText = OS.GetSysColor(COLOR_HIGHLIGHTTEXT);
              clrTextBk = clrSelection = OS.GetSysColor(COLOR_HIGHLIGHT);
            } else if ((style & SWT.HIDE_SELECTION) == 0) {
              clrTextBk = clrSelection = OS.GetSysColor(COLOR_3DFACE);
            }
            if (clrSelection != (-1)) {
              RECT rect =
                  item.getBounds(
                      ((int) (nmcd.dwItemSpec)),
                      nmcd.iSubItem,
                      true,
                      nmcd.iSubItem != 0,
                      true,
                      false,
                      hDC);
              fillBackground(hDC, clrSelection, rect);
            }
          }
        }
      }
    }
    if (!ignoreDrawForeground) {
      boolean hasAttributes = true;
      if (((hFont == (-1)) && (clrText == (-1))) && (clrTextBk == (-1))) {
        if (((item.cellForeground == null) && (item.cellBackground == null))
            && (item.cellFont == null)) {
          int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
          int count = ((int) (OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0)));
          if (count == 1) {
            hasAttributes = false;
          }
        }
      }
      if (hasAttributes) {
        if (hFont == (-1)) {
          hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
        }
        OS.SelectObject(hDC, hFont);
        if (OS.IsWindowEnabled(handle)) {
          nmcd.clrText = (clrText == (-1)) ? getForegroundPixel() : clrText;
          if (clrTextBk == (-1)) {
            nmcd.clrTextBk = OS.CLR_NONE;
            if (selectionForeground == (-1)) {
              Control control = findBackgroundControl();
              if (control == null) {
                control = this;
              }
              if (control.backgroundImage == null) {
                if (((int) (OS.SendMessage(handle, LVM_GETBKCOLOR, 0, 0))) != OS.CLR_NONE) {
                  nmcd.clrTextBk = control.getBackgroundPixel();
                }
              }
            }
          } else {
            nmcd.clrTextBk = (selectionForeground != (-1)) ? OS.CLR_NONE : clrTextBk;
          }
          OS.MoveMemory(lParam, nmcd, sizeof);
        }
        code |= OS.CDRF_NEWFONT;
      }
    }
    if (OS.IsWindowEnabled(handle)) {
      if (clrTextBk != (-1)) {
        int oldColumn = ((int) (OS.SendMessage(handle, LVM_GETSELECTEDCOLUMN, 0, 0)));
        if ((oldColumn != (-1)) && (oldColumn == nmcd.iSubItem)) {
          int result = 0;
          int rgn = 0;
          if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
            rgn = OS.CreateRectRgn(0, 0, 0, 0);
            result = OS.GetUpdateRgn(handle, rgn, true);
          }
          OS.SendMessage(handle, LVM_SETSELECTEDCOLUMN, -1, 0);
          if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
            OS.ValidateRect(handle, null);
            if (result != OS.NULLREGION) {
              OS.InvalidateRgn(handle, rgn, true);
            }
            OS.DeleteObject(rgn);
          }
          code |= OS.CDRF_NOTIFYPOSTPAINT;
        }
      }
    } else {
      nmcd.clrText = OS.GetSysColor(COLOR_GRAYTEXT);
      if (findImageControl() != null) {
        nmcd.clrTextBk = OS.CLR_NONE;
      } else {
        nmcd.clrTextBk = OS.GetSysColor(COLOR_3DFACE);
      }
      nmcd.uItemState &= ~OS.CDIS_SELECTED;
      OS.MoveMemory(lParam, nmcd, sizeof);
      code |= OS.CDRF_NEWFONT;
    }
    return new LRESULT(code);
  }
}
