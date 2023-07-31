class PlaceHold {
  void setSortDirection(int direction) {
    if (OS.COMCTL32_MAJOR >= 6) {
      int index = parent.indexOf(this);
      if (index == (-1)) {
        return;
      }
      int hwnd = parent.handle;
      int hwndHeader = OS.SendMessage(hwnd, LVM_GETHEADER, 0, 0);
      HDITEM hdItem = new HDITEM();
      hdItem.mask = OS.HDI_FORMAT | OS.HDI_IMAGE;
      OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
      switch (direction) {
        case SWT.UP:
          hdItem.fmt &= ~(OS.HDF_IMAGE | OS.HDF_SORTDOWN);
          hdItem.fmt |= OS.HDF_SORTUP;
          break;
        case SWT.DOWN:
          hdItem.fmt &= ~(OS.HDF_IMAGE | OS.HDF_SORTUP);
          hdItem.fmt |= OS.HDF_SORTDOWN;
          break;
        case SWT.NONE:
          hdItem.fmt &= ~(OS.HDF_SORTUP | OS.HDF_SORTDOWN);
          if (image != null) {
            hdItem.fmt |= OS.HDF_IMAGE;
            hdItem.iImage = parent.imageIndexHeader(image);
          } else {
            hdItem.fmt &= ~OS.HDF_IMAGE;
            hdItem.mask &= ~OS.HDI_IMAGE;
          }
          break;
      }
      OS.SendMessage(hwndHeader, HDM_SETITEM, index, hdItem);
      parent.forceResize();
      RECT rect = new RECT();
      OS.GetClientRect(hwnd, rect);
      if (((int) (OS.SendMessage(hwnd, LVM_GETBKCOLOR, 0, 0))) != OS.CLR_NONE) {
        int oldColumn = ((int) (OS.SendMessage(hwnd, LVM_GETSELECTEDCOLUMN, 0, 0)));
        int newColumn = (direction == SWT.NONE) ? -1 : index;
        OS.SendMessage(hwnd, LVM_SETSELECTEDCOLUMN, newColumn, 0);
        RECT headerRect = new RECT();
        if (oldColumn != (-1)) {
          if (OS.SendMessage(hwndHeader, HDM_GETITEMRECT, oldColumn, headerRect) != 0) {
            OS.MapWindowPoints(hwndHeader, hwnd, headerRect, 2);
            rect.left = headerRect.left;
            rect.right = headerRect.right;
            OS.InvalidateRect(hwnd, rect, true);
          }
        }
      }
      RECT headerRect = new RECT();
      if (OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect) != 0) {
        OS.MapWindowPoints(hwndHeader, hwnd, headerRect, 2);
        rect.left = headerRect.left;
        rect.right = headerRect.right;
        OS.InvalidateRect(hwnd, rect, true);
      }
    } else {
      switch (direction) {
        case SWT.UP:
        case SWT.DOWN:
          setImage(display.getSortImage(direction), true, true);
          break;
        case SWT.NONE:
          setImage(image, false, false);
          break;
      }
    }
  }
}
