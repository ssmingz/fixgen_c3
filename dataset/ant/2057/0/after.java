class PlaceHold {
  public void setRemoteTofile(String aToUri) {
    validateRemoteUri("remoteToFile", aToUri);
    setToUri(aToUri);
    this.isToRemote = true;
  }
}
