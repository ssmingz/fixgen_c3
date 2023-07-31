class PlaceHold {
  void releaseHandle() {
    super.releaseHandle();
    boxHandle = arrowHandle = separatorHandle = labelHandle = imageHandle = 0;
  }
}
