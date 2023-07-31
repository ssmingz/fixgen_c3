class PlaceHold {
  void configure() {
    _connectParent();
    OS.gtk_container_add(eventBoxHandle, scrolledHandle);
    OS.gtk_container_add(scrolledHandle, handle);
  }
}
