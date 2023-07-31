class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    if (labelHandle != 0) {
      OS.g_object_unref(labelHandle);
    }
    text = null;
  }
}
