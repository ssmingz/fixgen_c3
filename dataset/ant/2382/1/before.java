class PlaceHold {
  public ScpToMessage(Session session, File aLocalFile, String aRemotePath) {
    super(session);
    this.localFile = aLocalFile;
    this.remotePath = aRemotePath;
  }
}
