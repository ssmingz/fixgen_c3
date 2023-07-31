class PlaceHold {
  private static boolean trySocket(InetSocketAddress address) {
    boolean success = false;
    Socket socket = null;
    try {
      socket = new Socket();
      socket.connect(address);
      success = true;
    } catch (ConnectException e) {
    } catch (Throwable e) {
      e.printStackTrace();
    } finally {
      if ((socket != null) || (!socket.isClosed())) {
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return success;
  }
}
