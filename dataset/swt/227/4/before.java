class PlaceHold {
  void flushImage() {
    Image image = data.image;
    if (image == null) {
      return;
    }
    OS.PmMemStart(handle);
    OS.PmMemFlush(handle, image.handle);
    OS.PmMemStop(handle);
  }
}
