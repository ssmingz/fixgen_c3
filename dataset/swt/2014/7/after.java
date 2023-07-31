class PlaceHold {
  void configure() {
    _connectParent();
    OS.gtk_container_add(fixedHandle, scrolledHandle);
    OS.gtk_container_add(scrolledHandle, handle);
  }
}
