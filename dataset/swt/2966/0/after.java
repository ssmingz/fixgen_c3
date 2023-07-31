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
          }
          break;
      }
      OS.SendMessage(hwndHeader, HDM_SETITEM, index, hdItem);
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
