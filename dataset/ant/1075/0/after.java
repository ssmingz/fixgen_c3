class PlaceHold {
  public byte[] getCentralDirectoryExtra() {
    return ExtraFieldUtils.mergeCentralDirectoryData(getExtraFields(true));
  }
}
