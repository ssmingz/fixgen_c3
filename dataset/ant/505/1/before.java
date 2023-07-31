class PlaceHold {
  public void setEncoding(final String encoding) {
    this.encoding = encoding;
    this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
    useEFS &= ZipEncodingHelper.isUTF8(encoding);
  }
}
