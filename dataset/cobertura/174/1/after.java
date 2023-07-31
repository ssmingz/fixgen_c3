class PlaceHold {
  private void closeSocket(ServerSocket socket) {
    try {
      if ((socket != null) && socket.isClosed()) {
        socket.close();
      }
    } catch (Throwable t) {
      t.printStackTrace(System.err);
    }
  }
}
