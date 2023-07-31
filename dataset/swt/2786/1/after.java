class PlaceHold {
  void updateOrientation() {
    super.updateOrientation();
    long hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    if (hwndHeader != 0) {
      int bits = OS.GetWindowLong(hwndHeader, GWL_EXSTYLE);
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        bits |= OS.WS_EX_LAYOUTRTL;
      } else {
        bits &= ~OS.WS_EX_LAYOUTRTL;
      }
      bits &= ~OS.WS_EX_RTLREADING;
      OS.SetWindowLong(hwndHeader, GWL_EXSTYLE, bits);
      OS.InvalidateRect(hwndHeader, null, true);
      RECT rect = new RECT();
      OS.GetWindowRect(handle, rect);
      int width = rect.right - rect.left;
      int height = rect.bottom - rect.top;
      OS.SetWindowPos(handle, 0, 0, 0, width - 1, height - 1, OS.SWP_NOMOVE | OS.SWP_NOZORDER);
      OS.SetWindowPos(handle, 0, 0, 0, width, height, OS.SWP_NOMOVE | OS.SWP_NOZORDER);
    }
    if ((style & SWT.CHECK) != 0) {
      fixCheckboxImageListColor(false);
    }
    if (imageList != null) {
      Point size = imageList.getImageSize();
      display.releaseImageList(imageList);
      imageList = display.getImageList(style & SWT.RIGHT_TO_LEFT, size.x, size.y);
      int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
      for (int i = 0; i < count; i++) {
        TableItem item = _getItem(i, false);
        if (item != null) {
          Image image = item.image;
          if (image != null) {
            int index = imageList.indexOf(image);
            if (index == (-1)) {
              imageList.add(image);
            }
          }
        }
      }
      long hImageList = imageList.getHandle();
      OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, hImageList);
    }
    if (hwndHeader != 0) {
      if (headerImageList != null) {
        Point size = headerImageList.getImageSize();
        display.releaseImageList(headerImageList);
        headerImageList = display.getImageList(style & SWT.RIGHT_TO_LEFT, size.x, size.y);
        if (columns != null) {
          for (int i = 0; i < columns.length; i++) {
            TableColumn column = columns[i];
            if (column != null) {
              Image image = column.image;
              if (image != null) {
                if (OS.COMCTL32_MAJOR < 6) {
                  HDITEM hdItem = new HDITEM();
                  hdItem.mask = OS.HDI_FORMAT;
                  OS.SendMessage(hwndHeader, HDM_GETITEM, i, hdItem);
                  if ((hdItem.fmt & OS.HDF_IMAGE) != 0) {
                    int index = headerImageList.indexOf(image);
                    if (index == (-1)) {
                      headerImageList.add(image);
                    }
                    hdItem.mask = OS.HDI_IMAGE;
                    hdItem.iImage = index;
                    OS.SendMessage(hwndHeader, HDM_SETITEM, i, hdItem);
                  }
                } else {
                  LVCOLUMN lvColumn = new LVCOLUMN();
                  lvColumn.mask = OS.LVCF_FMT;
                  OS.SendMessage(hwndHeader, LVM_GETCOLUMN, i, lvColumn);
                  if ((lvColumn.fmt & OS.LVCFMT_IMAGE) != 0) {
                    int index = headerImageList.indexOf(image);
                    if (index == (-1)) {
                      headerImageList.add(image);
                    }
                    lvColumn.iImage = index;
                    lvColumn.mask = OS.LVCF_IMAGE;
                    OS.SendMessage(hwndHeader, LVM_SETCOLUMN, i, lvColumn);
                  }
                }
              }
            }
          }
        }
        long hHeaderImageList = headerImageList.getHandle();
        OS.SendMessage(hwndHeader, HDM_SETIMAGELIST, 0, hHeaderImageList);
      }
    }
  }
}
