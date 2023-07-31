class PlaceHold {
  void updateOrientation() {
    super.updateOrientation();
    RECT rect = new RECT();
    OS.GetWindowRect(handle, rect);
    int width = rect.right - rect.left;
    int height = rect.bottom - rect.top;
    OS.SetWindowPos(handle, 0, 0, 0, width - 1, height - 1, OS.SWP_NOMOVE | OS.SWP_NOZORDER);
    OS.SetWindowPos(handle, 0, 0, 0, width, height, OS.SWP_NOMOVE | OS.SWP_NOZORDER);
    if (hwndParent != 0) {
      int bits = OS.GetWindowLong(hwndParent, GWL_EXSTYLE);
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        bits |= OS.WS_EX_LAYOUTRTL;
      } else {
        bits &= ~OS.WS_EX_LAYOUTRTL;
      }
      bits &= ~OS.WS_EX_RTLREADING;
      OS.SetWindowLong(hwndParent, GWL_EXSTYLE, bits);
      rect = new RECT();
      OS.GetWindowRect(hwndParent, rect);
      width = rect.right - rect.left;
      height = rect.bottom - rect.top;
      OS.SetWindowPos(hwndParent, 0, 0, 0, width - 1, height - 1, OS.SWP_NOMOVE | OS.SWP_NOZORDER);
      OS.SetWindowPos(hwndParent, 0, 0, 0, width, height, OS.SWP_NOMOVE | OS.SWP_NOZORDER);
    }
    if (hwndHeader != 0) {
      int bits = OS.GetWindowLong(hwndHeader, GWL_EXSTYLE);
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        bits |= OS.WS_EX_LAYOUTRTL;
      } else {
        bits &= ~OS.WS_EX_LAYOUTRTL;
      }
      OS.SetWindowLong(hwndHeader, GWL_EXSTYLE, bits);
      OS.InvalidateRect(hwndHeader, null, true);
    }
    if ((style & SWT.CHECK) != 0) {
      setCheckboxImageList();
    }
    if (imageList != null) {
      Point size = imageList.getImageSize();
      display.releaseImageList(imageList);
      imageList = display.getImageList(style & SWT.RIGHT_TO_LEFT, size.x, size.y);
      for (int i = 0; i < items.length; i++) {
        TreeItem item = items[i];
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
      OS.SendMessage(handle, TVM_SETIMAGELIST, TVSIL_NORMAL, hImageList);
    }
    if (hwndHeader != 0) {
      if (headerImageList != null) {
        Point size = headerImageList.getImageSize();
        display.releaseImageList(headerImageList);
        headerImageList = display.getImageList(style & SWT.RIGHT_TO_LEFT, size.x, size.y);
        if (columns != null) {
          for (int i = 0; i < columns.length; i++) {
            TreeColumn column = columns[i];
            if (column != null) {
              Image image = column.image;
              if (image != null) {
                HDITEM hdItem = new HDITEM();
                hdItem.mask = OS.HDI_FORMAT;
                OS.SendMessage(hwndHeader, HDM_GETITEM, i, hdItem);
                if ((hdItem.fmt & OS.HDF_IMAGE) != 0) {
                  int index = headerImageList.indexOf(image);
                  if (index == (-1)) {
                    index = headerImageList.add(image);
                  }
                  hdItem.mask = OS.HDI_IMAGE;
                  hdItem.iImage = index;
                  OS.SendMessage(hwndHeader, HDM_SETITEM, i, hdItem);
                }
              }
            }
          }
        }
        long hImageListHeader = headerImageList.getHandle();
        OS.SendMessage(hwndHeader, HDM_SETIMAGELIST, 0, hImageListHeader);
      }
    }
  }
}
