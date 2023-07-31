class PlaceHold {
  void showWidget() {
    super.showWidget();
    if (socketHandle != 0) {
      OS.gtk_widget_show(socketHandle);
      embeddedHandle = OS.gtk_socket_get_id(socketHandle);
    }
  }
}
