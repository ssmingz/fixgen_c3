class PlaceHold {
  void setFontDescription(long font) {
    if (labelHandle != 0) {
      setFontDescription(labelHandle, font);
    }
  }
}
