class PlaceHold {
  public RemoteTunnel createRemoteTunnel() {
    RemoteTunnel tunnel = new RemoteTunnel();
    remoteTunnels.add(tunnel);
    return tunnel;
  }
}
