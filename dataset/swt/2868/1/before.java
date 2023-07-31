class PlaceHold {
  void updateOrientation() {
    super.updateOrientation();
    int hwndChild = OS.GetWindow(handle, GW_CHILD);
    while (hwndChild != 0) {
      TCHAR buffer = new TCHAR(0, 128);
      OS.GetClassName(hwndChild, buffer, buffer.length());
      String className = buffer.toString(0, buffer.strlen());
      if (className.equals("msctls_updown32")) {
        int bits = OS.GetWindowLong(hwndChild, GWL_EXSTYLE);
        if ((style & SWT.RIGHT_TO_LEFT) != 0) {
          bits |= OS.WS_EX_LAYOUTRTL;
        } else {
          bits &= ~OS.WS_EX_LAYOUTRTL;
        }
        OS.SetWindowLong(hwndChild, GWL_EXSTYLE, bits);
        OS.InvalidateRect(hwndChild, null, true);
        break;
      }
      hwndChild = OS.GetWindow(hwndChild, GW_HWNDNEXT);
    }
    RECT rect = new RECT();
    OS.GetWindowRect(handle, rect);
    int width = rect.right - rect.left;
    int height = rect.bottom - rect.top;
    OS.SetWindowPos(handle, 0, 0, 0, width - 1, height - 1, OS.SWP_NOMOVE | OS.SWP_NOZORDER);
    OS.SetWindowPos(handle, 0, 0, 0, width, height, OS.SWP_NOMOVE | OS.SWP_NOZORDER);
    if (imageList != null) {
      Point size = imageList.getImageSize();
      display.releaseImageList(imageList);
      imageList = display.getImageList(style & SWT.RIGHT_TO_LEFT, size.x, size.y);
      int hImageList = imageList.getHandle();
      OS.SendMessage(handle, TCM_SETIMAGELIST, 0, hImageList);
      TCITEM tcItem = new TCITEM();
      tcItem.mask = OS.TCIF_IMAGE;
      for (int i = 0; i < items.length; i++) {
        TabItem item = items[0];
        if (item == null) {
          break;
        }
        Image image = item.image;
        if (image != null) {
          tcItem.iImage = imageIndex(image);
          OS.SendMessage(handle, TCM_SETITEM, i, tcItem);
        }
      }
    }
  }
}
