class PlaceHold {
  void updateText() {
    if (labelCIcon != 0) {
      destroyCIcon(labelCIcon);
    }
    labelCIcon = 0;
    ControlButtonContentInfo inContent = new ControlButtonContentInfo();
    if (text.length() > 0) {
      char[] buffer = new char[text.length()];
      text.getChars(0, buffer.length, buffer, 0);
      int i = 0;
      int j = 0;
      while (i < buffer.length) {
        if ((buffer[j++] = buffer[i++]) == Mnemonic) {
          if (i == buffer.length) {
            continue;
          }
          if (buffer[i] == Mnemonic) {
            i++;
            continue;
          }
          j--;
        }
      }
      Font font = parent.getFont();
      GC gc = new GC(parent);
      Point size = gc.stringExtent(text);
      gc.dispose();
      Display display = getDisplay();
      Image image = new Image(display, size.x, size.y);
      gc = new GC(image);
      gc.setFont(font);
      gc.drawString(text, 0, 0);
      gc.dispose();
      ImageData data = image.getImageData();
      data.transparentPixel = 0xffffffff;
      image.dispose();
      image = new Image(display, data, data.getTransparencyMask());
      labelCIcon = createCIcon(image);
      image.dispose();
      inContent.contentType = ((short) (OS.kControlContentCIconHandle));
      inContent.iconRef = labelCIcon;
    }
    OS.SetBevelButtonContentInfo(labelHandle, inContent);
    redrawWidget(labelHandle, false);
    Point size = computeSize();
    setSize(size.x, size.y, true);
  }
}
