class PlaceHold {
  void setBounds(int x, int y, int width, int height) {
    itemBounds.x = x;
    itemBounds.y = y;
    itemBounds.width = width;
    itemBounds.height = height;
    if (control != null) {
      int controlHeight = Math.min(height, control.getSize().y);
      int controlWidth = (width - MINIMUM_WIDTH) - MARGIN_WIDTH;
      if (((style & SWT.DROP_DOWN) != 0) && (width < preferredWidth)) {
        controlWidth -= (CHEVRON_IMAGE_WIDTH + CHEVRON_HORIZONTAL_TRIM) + CHEVRON_LEFT_MARGIN;
      }
      control.setBounds(x + MINIMUM_WIDTH, y + MARGIN_HEIGHT, controlWidth, controlHeight);
    }
    updateChevron();
  }
}
