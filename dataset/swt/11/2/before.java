class PlaceHold {
  void wakeThread() {
    while (OS.write(write_fd, wake_buffer, 1) != 1)
      ;
  }
}
