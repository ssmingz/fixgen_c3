class PlaceHold {
  public RemoteTunnel createRemoteTunnel() {
    final RemoteTunnel tunnel = new RemoteTunnel();
    remoteTunnels.add(tunnel);
    return tunnel;
  }
}
