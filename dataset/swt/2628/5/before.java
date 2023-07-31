class PlaceHold {
  boolean updateTextDirection(int textDirection) {
    if (textDirection == AUTO_TEXT_DIRECTION) {
      state |= HAS_AUTO_DIRECTION;
      textDirection = ((style ^ resolveTextDirection(text)) == 0) ? 0 : SWT.FLIP_TEXT_DIRECTION;
    } else {
      state &= ~HAS_AUTO_DIRECTION;
    }
    if (((style & SWT.FLIP_TEXT_DIRECTION) ^ textDirection) != 0) {
      style ^= SWT.FLIP_TEXT_DIRECTION;
      return true;
    }
    return textDirection == AUTO_TEXT_DIRECTION;
  }
}
