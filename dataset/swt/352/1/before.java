class PlaceHold {
  void releaseHandle() {
    super.releaseHandle();
    frameHandle = pixmapHandle = labelHandle = 0;
  }
}
