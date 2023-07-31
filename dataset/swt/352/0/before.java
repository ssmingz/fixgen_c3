class PlaceHold {
  void releaseHandle() {
    super.releaseHandle();
    boxHandle = pixmapHandle = labelHandle = arrowHandle = 0;
  }
}
