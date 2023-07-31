class PlaceHold {
  int internalGetMinimumWidth() {
    int width = minimumSize.x;
    width += MINIMUM_WIDTH + MARGIN_WIDTH;
    if (((style & SWT.DROP_DOWN) != 0) && (width < preferredWidth)) {
      width += (CHEVRON_IMAGE_WIDTH + CHEVRON_HORIZONTAL_TRIM) + CHEVRON_LEFT_MARGIN;
    }
    return width;
  }
}
