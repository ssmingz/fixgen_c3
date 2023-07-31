class PlaceHold {
  LRESULT wmNotifyChild(int wParam, int lParam) {
    NMHDR hdr = new NMHDR();
    OS.MoveMemory(hdr, lParam, sizeof);
    switch (hdr.code) {
      case OS.LVN_MARQUEEBEGIN:
        return LRESULT.ONE;
      case OS.LVN_BEGINDRAG:
      case OS.LVN_BEGINRDRAG:
        dragStarted = true;
        if (hdr.code == OS.LVN_BEGINDRAG) {
          postEvent(DragDetect);
        }
        break;
      case OS.LVN_COLUMNCLICK:
        {
          NMLISTVIEW pnmlv = new NMLISTVIEW();
          OS.MoveMemory(pnmlv, lParam, sizeof);
          TableColumn column = columns[pnmlv.iSubItem];
          if (column != null) {
            column.postEvent(Selection);
          }
          break;
        }
      case OS.LVN_ITEMACTIVATE:
        {
          NMLISTVIEW pnmlv = new NMLISTVIEW();
          OS.MoveMemory(pnmlv, lParam, sizeof);
          if (pnmlv.iItem != (-1)) {
            Event event = new Event();
            event.item = items[pnmlv.iItem];
            postEvent(DefaultSelection, event);
          }
          break;
        }
      case OS.LVN_ITEMCHANGED:
        {
          if (!ignoreSelect) {
            NMLISTVIEW pnmlv = new NMLISTVIEW();
            OS.MoveMemory(pnmlv, lParam, sizeof);
            if ((pnmlv.iItem != (-1)) && ((pnmlv.uChanged & OS.LVIF_STATE) != 0)) {
              int oldBits = pnmlv.uOldState & OS.LVIS_STATEIMAGEMASK;
              int newBits = pnmlv.uNewState & OS.LVIS_STATEIMAGEMASK;
              if (oldBits != newBits) {
                Event event = new Event();
                event.item = items[pnmlv.iItem];
                event.detail = SWT.CHECK;
                postEvent(Selection, event);
              } else {
                boolean isFocus = (pnmlv.uNewState & OS.LVIS_FOCUSED) != 0;
                int index = OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED);
                if ((style & SWT.MULTI) != 0) {
                  if (OS.GetKeyState(VK_CONTROL) < 0) {
                    if (!isFocus) {
                      if (index == pnmlv.iItem) {
                        boolean isSelected = (pnmlv.uNewState & OS.LVIS_SELECTED) != 0;
                        boolean wasSelected = (pnmlv.uOldState & OS.LVIS_SELECTED) != 0;
                        isFocus = isSelected != wasSelected;
                      }
                    } else {
                      isFocus = mouseDown;
                    }
                  }
                }
                if (OS.GetKeyState(VK_SPACE) < 0) {
                  isFocus = true;
                }
                if (isFocus) {
                  Event event = new Event();
                  if (index != (-1)) {
                    event.item = items[index];
                  }
                  postEvent(Selection, event);
                }
              }
            }
          }
          break;
        }
    }
    return super.wmNotifyChild(wParam, lParam);
  }
}
