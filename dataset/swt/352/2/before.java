class PlaceHold {
  void releaseHandle() {
    super.releaseHandle();
    pageHandle = labelHandle = pixmapHandle = 0;
  }
}
