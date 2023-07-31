class PlaceHold {
  void releaseHandle() {
    super.releaseHandle();
    boxHandle = arrowHandle = separatorHandle = labelHandle = pixmapHandle = 0;
  }
}
