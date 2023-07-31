class PlaceHold {
  public byte[] getCentralDirectoryData() {
    if (centralData != null) {
      return ZipUtil.copy(centralData);
    }
    return getLocalFileDataData();
  }
}
