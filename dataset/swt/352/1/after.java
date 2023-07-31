class PlaceHold {
  void releaseHandle() {
    super.releaseHandle();
    frameHandle = imageHandle = labelHandle = 0;
  }
}
