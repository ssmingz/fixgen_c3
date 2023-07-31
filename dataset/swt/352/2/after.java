class PlaceHold {
  void releaseHandle() {
    super.releaseHandle();
    pageHandle = labelHandle = imageHandle = 0;
  }
}
