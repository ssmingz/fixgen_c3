class PlaceHold {
  @Override
  int resolveTextDirection() {
    return BidiUtil.resolveTextDirection(text);
  }
}
