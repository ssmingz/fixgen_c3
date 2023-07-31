class PlaceHold {
  void _setImage(Image image) {
    if ((style & SWT.COMMAND) != 0) {
      return;
    }
    if (OS.COMCTL32_MAJOR >= 6) {
      if (imageList != null) {
        imageList.dispose();
      }
      imageList = null;
      if (image != null) {
        imageList = new ImageList(style & SWT.RIGHT_TO_LEFT);
        if (OS.IsWindowEnabled(handle)) {
          imageList.add(image);
        } else {
          if (disabledImage != null) {
            disabledImage.dispose();
          }
          disabledImage = new Image(display, image, SWT.IMAGE_DISABLE);
          imageList.add(disabledImage);
        }
        BUTTON_IMAGELIST buttonImageList = new BUTTON_IMAGELIST();
        buttonImageList.himl = imageList.getHandle();
        int oldBits = OS.GetWindowLong(handle, GWL_STYLE);
        int newBits = oldBits;
        newBits &= ~((OS.BS_LEFT | OS.BS_CENTER) | OS.BS_RIGHT);
        if ((style & SWT.LEFT) != 0) {
          newBits |= OS.BS_LEFT;
        }
        if ((style & SWT.CENTER) != 0) {
          newBits |= OS.BS_CENTER;
        }
        if ((style & SWT.RIGHT) != 0) {
          newBits |= OS.BS_RIGHT;
        }
        if (text.length() == 0) {
          if ((style & SWT.LEFT) != 0) {
            buttonImageList.uAlign = OS.BUTTON_IMAGELIST_ALIGN_LEFT;
          }
          if ((style & SWT.CENTER) != 0) {
            buttonImageList.uAlign = OS.BUTTON_IMAGELIST_ALIGN_CENTER;
          }
          if ((style & SWT.RIGHT) != 0) {
            buttonImageList.uAlign = OS.BUTTON_IMAGELIST_ALIGN_RIGHT;
          }
        } else {
          buttonImageList.uAlign = OS.BUTTON_IMAGELIST_ALIGN_LEFT;
          buttonImageList.margin_left = computeLeftMargin();
          buttonImageList.margin_right = MARGIN;
          newBits &= ~(OS.BS_CENTER | OS.BS_RIGHT);
          newBits |= OS.BS_LEFT;
        }
        if (newBits != oldBits) {
          OS.SetWindowLong(handle, GWL_STYLE, newBits);
          OS.InvalidateRect(handle, null, true);
        }
        OS.SendMessage(handle, BCM_SETIMAGELIST, 0, buttonImageList);
      } else {
        OS.SendMessage(handle, BCM_SETIMAGELIST, 0, 0);
      }
      OS.InvalidateRect(handle, null, true);
    } else {
      if (image2 != null) {
        image2.dispose();
      }
      image2 = null;
      int hImage = 0;
      int imageBits = 0;
      int fImageType = 0;
      if (image != null) {
        switch (image.type) {
          case SWT.BITMAP:
            {
              Rectangle rect = image.getBounds();
              ImageData data = image.getImageData();
              switch (data.getTransparencyType()) {
                case SWT.TRANSPARENCY_PIXEL:
                  if ((rect.width <= ICON_WIDTH) && (rect.height <= ICON_HEIGHT)) {
                    image2 = new Image(display, data, data.getTransparencyMask());
                    hImage = image2.handle;
                    imageBits = OS.BS_ICON;
                    fImageType = OS.IMAGE_ICON;
                    break;
                  }
                case SWT.TRANSPARENCY_ALPHA:
                  image2 = new Image(display, rect.width, rect.height);
                  GC gc = new GC(image2);
                  gc.setBackground(getBackground());
                  gc.fillRectangle(rect);
                  gc.drawImage(image, 0, 0);
                  gc.dispose();
                  hImage = image2.handle;
                  imageBits = OS.BS_BITMAP;
                  fImageType = OS.IMAGE_BITMAP;
                  break;
                case SWT.TRANSPARENCY_NONE:
                  hImage = image.handle;
                  imageBits = OS.BS_BITMAP;
                  fImageType = OS.IMAGE_BITMAP;
                  break;
              }
              break;
            }
          case SWT.ICON:
            {
              hImage = image.handle;
              imageBits = OS.BS_ICON;
              fImageType = OS.IMAGE_ICON;
              break;
            }
        }
        if ((style & SWT.RIGHT_TO_LEFT) != 0) {
          if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(4, 10))) {
            Rectangle rect = image.getBounds();
            int hDC = OS.GetDC(handle);
            int dstHdc = OS.CreateCompatibleDC(hDC);
            int hBitmap = OS.CreateCompatibleBitmap(hDC, rect.width, rect.height);
            int oldBitmap = OS.SelectObject(dstHdc, hBitmap);
            OS.SetLayout(dstHdc, LAYOUT_RTL);
            if (fImageType == OS.IMAGE_BITMAP) {
              int srcHdc = OS.CreateCompatibleDC(hDC);
              int oldSrcBitmap = OS.SelectObject(srcHdc, hImage);
              OS.SetLayout(dstHdc, 0);
              OS.BitBlt(dstHdc, 0, 0, rect.width, rect.height, srcHdc, 0, 0, SRCCOPY);
              OS.SelectObject(srcHdc, oldSrcBitmap);
              OS.DeleteDC(srcHdc);
            } else {
              Control control = findBackgroundControl();
              if (control == null) {
                control = this;
              }
              int newBrush = OS.CreateSolidBrush(control.getBackgroundPixel());
              int oldBrush = OS.SelectObject(dstHdc, newBrush);
              OS.PatBlt(dstHdc, 0, 0, rect.width, rect.height, PATCOPY);
              OS.DrawIconEx(dstHdc, 0, 0, hImage, 0, 0, 0, 0, DI_NORMAL);
              OS.SelectObject(dstHdc, oldBrush);
              OS.DeleteObject(newBrush);
            }
            OS.SelectObject(dstHdc, oldBitmap);
            OS.DeleteDC(dstHdc);
            OS.ReleaseDC(handle, hDC);
            if (image2 != null) {
              image2.dispose();
            }
            image2 = Image.win32_new(display, BITMAP, hBitmap);
            imageBits = OS.BS_BITMAP;
            fImageType = OS.IMAGE_BITMAP;
            hImage = hBitmap;
          }
        }
      }
      int newBits = OS.GetWindowLong(handle, GWL_STYLE);
      int oldBits = newBits;
      newBits &= ~(OS.BS_BITMAP | OS.BS_ICON);
      newBits |= imageBits;
      if (newBits != oldBits) {
        OS.SetWindowLong(handle, GWL_STYLE, newBits);
      }
      OS.SendMessage(handle, BM_SETIMAGE, fImageType, hImage);
    }
  }
}
