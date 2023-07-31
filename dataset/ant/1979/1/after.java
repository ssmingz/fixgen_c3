class PlaceHold {
  public LocalTunnel createLocalTunnel() {
    final LocalTunnel tunnel = new LocalTunnel();
    localTunnels.add(tunnel);
    return tunnel;
  }
}
