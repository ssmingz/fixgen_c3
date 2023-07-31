class PlaceHold {
  public void setEncoding(final String encoding) {
    this.encoding = encoding;
    this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
    useUTF8Flag &= ZipEncodingHelper.isUTF8(encoding);
  }
}
