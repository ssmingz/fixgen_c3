class PlaceHold {
  void flushImage() {
    Image image = data.image;
    if (image == null) {
      return;
    }
    int prevContext = OS.PmMemStart(handle);
    OS.PmMemFlush(handle, image.handle);
    OS.PmMemStop(handle);
    OS.PhDCSetCurrent(prevContext);
  }
}
