class PlaceHold {
  Image getDragSourceImage(DragSourceEvent event) {
    if (dragSourceImage != null) {
      dragSourceImage.dispose();
    }
    dragSourceImage = null;
    if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(5, 1))) {
      SHDRAGIMAGE shdi = new SHDRAGIMAGE();
      int DI_GETDRAGIMAGE = OS.RegisterWindowMessage(new TCHAR(0, "ShellGetDragImage", true));
      if (OS.SendMessage(handle, DI_GETDRAGIMAGE, 0, shdi) != 0) {
        event.x += shdi.ptOffset.x;
        event.y += shdi.ptOffset.y;
        int hImage = shdi.hbmpDragImage;
        if (hImage != 0) {
          BITMAP bm = new BITMAP();
          OS.GetObject(hImage, sizeof, bm);
          int srcWidth = bm.bmWidth;
          int srcHeight = bm.bmHeight;
          int hdc = OS.GetDC(0);
          int srcHdc = OS.CreateCompatibleDC(hdc);
          int oldSrcBitmap = OS.SelectObject(srcHdc, hImage);
          int memHdc = OS.CreateCompatibleDC(hdc);
          BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
          bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
          bmiHeader.biWidth = srcWidth;
          bmiHeader.biHeight = -srcHeight;
          bmiHeader.biPlanes = 1;
          bmiHeader.biBitCount = 32;
          bmiHeader.biCompression = OS.BI_RGB;
          byte[] bmi = new byte[BITMAPINFOHEADER.sizeof];
          OS.MoveMemory(bmi, bmiHeader, sizeof);
          int[] pBits = new int[1];
          int memDib = OS.CreateDIBSection(0, bmi, DIB_RGB_COLORS, pBits, 0, 0);
          if (memDib == 0) {
            SWT.error(ERROR_NO_HANDLES);
          }
          int oldMemBitmap = OS.SelectObject(memHdc, memDib);
          BITMAP dibBM = new BITMAP();
          OS.GetObject(memDib, sizeof, dibBM);
          int sizeInBytes = dibBM.bmWidthBytes * dibBM.bmHeight;
          OS.BitBlt(memHdc, 0, 0, srcWidth, srcHeight, srcHdc, 0, 0, SRCCOPY);
          byte[] srcData = new byte[sizeInBytes];
          OS.MoveMemory(srcData, dibBM.bmBits, sizeInBytes);
          byte[] alphaData = new byte[srcWidth * srcHeight];
          int spinc = dibBM.bmWidthBytes - (srcWidth * 4);
          int ap = 0;
          int sp = 3;
          for (int y = 0; y < srcHeight; ++y) {
            for (int x = 0; x < srcWidth; ++x) {
              alphaData[ap++] = srcData[sp];
              sp += 4;
            }
            sp += spinc;
          }
          PaletteData palette = new PaletteData(0xff00, 0xff0000, 0xff000000);
          ImageData data =
              new ImageData(srcWidth, srcHeight, bm.bmBitsPixel, palette, bm.bmWidthBytes, srcData);
          data.alphaData = alphaData;
          data.transparentPixel = shdi.crColorKey;
          dragSourceImage = new Image(control.getDisplay(), data);
          OS.SelectObject(memHdc, oldMemBitmap);
          OS.DeleteDC(memHdc);
          OS.DeleteObject(memDib);
          OS.SelectObject(srcHdc, oldSrcBitmap);
          OS.DeleteDC(srcHdc);
          OS.ReleaseDC(0, hdc);
          return dragSourceImage;
        }
      }
      return null;
    }
    Table table = ((Table) (control));
    if (table.isListening(EraseItem) || table.isListening(PaintItem)) {
      return null;
    }
    TableItem[] selection = table.getSelection();
    if (selection.length == 0) {
      return null;
    }
    int tableImageList = OS.SendMessage(table.handle, LVM_GETIMAGELIST, LVSIL_SMALL, 0);
    if (tableImageList != 0) {
      int count = Math.min(selection.length, 10);
      Rectangle bounds = selection[0].getBounds(0);
      for (int i = 1; i < count; i++) {
        bounds = bounds.union(selection[i].getBounds(0));
      }
      int hDC = OS.GetDC(0);
      int hDC1 = OS.CreateCompatibleDC(hDC);
      if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(4, 10))) {
        if ((table.getStyle() & SWT.RIGHT_TO_LEFT) != 0) {
          OS.SetLayout(hDC1, OS.LAYOUT_RTL | OS.LAYOUT_BITMAPORIENTATIONPRESERVED);
        }
      }
      int bitmap = OS.CreateCompatibleBitmap(hDC, bounds.width, bounds.height);
      int hOldBitmap = OS.SelectObject(hDC1, bitmap);
      RECT rect = new RECT();
      rect.right = bounds.width;
      rect.bottom = bounds.height;
      int hBrush = OS.GetStockObject(WHITE_BRUSH);
      OS.FillRect(hDC1, rect, hBrush);
      for (int i = 0; i < count; i++) {
        TableItem selected = selection[i];
        Rectangle cell = selected.getBounds(0);
        POINT pt = new POINT();
        int imageList =
            OS.SendMessage(table.handle, LVM_CREATEDRAGIMAGE, table.indexOf(selected), pt);
        OS.ImageList_Draw(imageList, 0, hDC1, cell.x - bounds.x, cell.y - bounds.y, ILD_SELECTED);
        OS.ImageList_Destroy(imageList);
      }
      OS.SelectObject(hDC1, hOldBitmap);
      OS.DeleteDC(hDC1);
      OS.ReleaseDC(0, hDC);
      Display display = table.getDisplay();
      dragSourceImage = Image.win32_new(display, BITMAP, bitmap);
      return dragSourceImage;
    }
    return null;
  }
}
