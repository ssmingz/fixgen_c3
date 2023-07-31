class PlaceHold {
  private String getHostname() {
    String hostname = "localhost";
    try {
      InetAddress localHost = InetAddress.getLocalHost();
      if (localHost != null) {
        hostname = localHost.getHostName();
      }
    } catch (UnknownHostException e) {
    }
    return hostname;
  }
}
