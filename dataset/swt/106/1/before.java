class PlaceHold {
  int processPaint(Object callData) {
    if (((style & SWT.SEPARATOR) != 0) && (control != null)) {
      return 0;
    }
    final Display display = getDisplay();
    MacRect bounds = new MacRect();
    OS.GetControlBounds(handle, bounds.getData());
    bounds.setLocation(0, 0);
    int width = bounds.getWidth();
    int height = bounds.getHeight();
    Drawable drawable =
        new Drawable() {
          public int internal_new_GC(GCData data) {
            data.device = display;
            data.foreground = parent.foreground;
            data.background = parent.background;
            data.font = parent.font.handle;
            data.controlHandle = handle;
            return OS.GetWindowPort(OS.GetControlOwner(handle));
          }

          public void internal_dispose_GC(int xGC, GCData data) {}
        };
    boolean hasCursor = hasCursor();
    GC gc = new GC(drawable);
    MacControlEvent me = ((MacControlEvent) (callData));
    Rectangle r = gc.carbon_focus(me.getDamageRegionHandle());
    if (!r.isEmpty()) {
      gc.fillRectangle(0, 0, width, height);
      if (((parent.style & SWT.FLAT) != 0) && set) {
        gc.setBackground(Color.carbon_new(display, 0xe0e0e0));
        gc.fillRoundRectangle(1, 1, width - 2, height - 2, 8, 8);
        gc.setForeground(display.getSystemColor(COLOR_GRAY));
        gc.drawRoundRectangle(1, 1, width - 3, height - 3, 8, 8);
      }
      if ((style & SWT.SEPARATOR) != 0) {
        OS.DrawThemeSeparator(bounds.getData(), kThemeStateActive);
      } else {
        Image currentImage = image;
        boolean enabled = getEnabled();
        short[] newInfo = new short[3];
        newInfo[1] = (set) ? OS.kThemeButtonOn : OS.kThemeButtonOff;
        if ((parent.style & SWT.FLAT) != 0) {
          if (hasCursor && enabled) {
            if (OS.StillDown()) {
              newInfo[0] = OS.kThemeStatePressed;
            } else {
              newInfo[0] = OS.kThemeStateActive;
            }
          } else {
            newInfo = null;
          }
          if ((enabled && hasCursor) && (hotImage != null)) {
            currentImage = hotImage;
          }
        } else {
          newInfo[0] = (hasCursor && OS.StillDown()) ? OS.kThemeStatePressed : OS.kThemeStateActive;
        }
        if (newInfo != null) {
          MacRect b = new MacRect(1, 1, width - 2, height - 2);
          OS.DrawThemeButton(b.getData(), kThemeSmallBevelButton, newInfo, fPrevInfo, 0, 0, 0);
        }
        fPrevInfo = newInfo;
        if (enabled) {
          gc.setForeground(parent.getForeground());
        } else {
          currentImage = disabledImage;
          if ((currentImage == null) && (image != null)) {
            currentImage = new Image(display, image, SWT.IMAGE_DISABLE);
          }
          Color disabledColor = display.getSystemColor(COLOR_WIDGET_NORMAL_SHADOW);
          gc.setForeground(disabledColor);
        }
        int textX = 0;
        int textY = 0;
        int textWidth = 0;
        int textHeight = 0;
        if (text.length() > 0) {
          int flags = (SWT.DRAW_DELIMITER | SWT.DRAW_TAB) | SWT.DRAW_MNEMONIC;
          Point textExtent = gc.textExtent(text, flags);
          textWidth = textExtent.x;
          textHeight = textExtent.y;
        }
        int imageX = 0;
        int imageY = 0;
        int imageWidth = 0;
        int imageHeight = 0;
        if (currentImage != null) {
          Rectangle imageBounds = currentImage.getBounds();
          imageWidth = imageBounds.width;
          imageHeight = imageBounds.height;
        }
        int spacing = 0;
        if ((textWidth != 0) && (imageWidth != 0)) {
          spacing = 2;
        }
        if ((parent.style & SWT.RIGHT) != 0) {
          imageX = (((width - imageWidth) - textWidth) - spacing) / 2;
          imageY = (height - imageHeight) / 2;
          textX = (spacing + imageX) + imageWidth;
          textY = (height - textHeight) / 2;
        } else {
          imageX = (width - imageWidth) / 2;
          imageY = (((height - imageHeight) - textHeight) - spacing) / 2;
          textX = (width - textWidth) / 2;
          textY = (spacing + imageY) + imageHeight;
        }
        if ((style & SWT.DROP_DOWN) != 0) {
          textX -= 6;
          imageX -= 6;
        }
        if (textWidth > 0) {
          int flags =
              ((SWT.DRAW_DELIMITER | SWT.DRAW_TAB) | SWT.DRAW_MNEMONIC) | SWT.DRAW_TRANSPARENT;
          gc.drawText(text, textX, textY, flags);
        }
        if (imageWidth > 0) {
          gc.drawImage(currentImage, imageX, imageY);
        }
        if ((style & SWT.DROP_DOWN) != 0) {
          int startX = width - 12;
          int startY = (height - 2) / 2;
          int[] arrow = new int[] {startX, startY, startX + 3, startY + 3, startX + 6, startY};
          gc.setBackground(gc.getForeground());
          gc.fillPolygon(arrow);
          gc.drawPolygon(arrow);
        }
        if ((!enabled) && (disabledImage == null)) {
          if (currentImage != null) {
            currentImage.dispose();
          }
        }
      }
    }
    gc.carbon_unfocus();
    gc.dispose();
    return 0;
  }
}
