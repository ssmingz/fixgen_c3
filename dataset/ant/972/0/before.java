class PlaceHold {
  public byte[] getCentralDirectoryData() {
    if (centralData != null) {
      return copy(centralData);
    }
    return getLocalFileDataData();
  }
}
