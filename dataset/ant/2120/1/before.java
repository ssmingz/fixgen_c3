class PlaceHold {
  public byte[] getLocalFileDataData() {
    if ((size != null) || (compressedSize != null)) {
      if ((size == null) || (compressedSize == null)) {
        throw new IllegalArgumentException(LFH_MUST_HAVE_BOTH_SIZES_MSG);
      }
      byte[] data = new byte[2 * DWORD];
      addSizes(data);
      return data;
    }
    return new byte[0];
  }
}
