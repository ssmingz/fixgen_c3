class PlaceHold {
  public void setRemoteTodir(String aToUri) {
    validateRemoteUri("remoteToDir", aToUri);
    setToUri(aToUri);
    this.isToRemote = true;
  }
}
