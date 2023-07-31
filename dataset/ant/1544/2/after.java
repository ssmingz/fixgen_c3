class PlaceHold {
  private String getHostname() {
    String hostname = "localhost";
    try {
      final InetAddress localHost = InetAddress.getLocalHost();
      if (localHost != null) {
        hostname = localHost.getHostName();
      }
    } catch (final UnknownHostException e) {
    }
    return hostname;
  }
}
