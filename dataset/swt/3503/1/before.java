class PlaceHold {
  int preferredWidth(GC gc, boolean isSelected) {
    CTabFolderEvent e = new CTabFolderEvent(this);
    e.widget = this;
    e.time = ((int) (System.currentTimeMillis()));
    e.doit = true;
    e.x = e.y = e.width = e.height = 0;
    e.item = this;
    for (int j = 0; j < parent.folderListeners.length; j++) {
      parent.folderListeners[j].getTabSize(e);
    }
    if (!e.doit) {
      return e.width;
    }
    if (isDisposed()) {
      return 0;
    }
    int w = 0;
    Image image = getImage();
    if ((image != null) && (isSelected || parent.showUnselectedImage)) {
      w += image.getBounds().width;
    }
    String text = getText();
    if (text != null) {
      if (w > 0) {
        w += INTERNAL_SPACING;
      }
      if (font == null) {
        w += gc.textExtent(text, FLAGS).x;
      } else {
        Font gcFont = gc.getFont();
        gc.setFont(font);
        w += gc.textExtent(text, FLAGS).x;
        gc.setFont(gcFont);
      }
    }
    if (parent.showClose || showClose) {
      if (isSelected || parent.showUnselectedClose) {
        if (w > 0) {
          w += INTERNAL_SPACING;
        }
        w += CTabFolder.BUTTON_SIZE;
      }
    }
    if ((!parent.simple) && isSelected) {
      w += CTabFolder.CURVE_INDENT;
    }
    return (w + LEFT_MARGIN) + RIGHT_MARGIN;
  }
}
