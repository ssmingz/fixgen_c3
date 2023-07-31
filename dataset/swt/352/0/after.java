class PlaceHold {
  void releaseHandle() {
    super.releaseHandle();
    boxHandle = imageHandle = labelHandle = arrowHandle = 0;
  }
}
