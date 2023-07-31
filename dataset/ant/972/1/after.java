class PlaceHold {
  public void setCentralDirectoryData(byte[] data) {
    centralData = ZipUtil.copy(data);
  }
}
