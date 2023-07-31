class PlaceHold {
  protected byte[] getBytes(String name) throws ZipException {
    if (encoding == null) {
      return name.getBytes();
    } else {
      try {
        return ZipEncodingHelper.encodeName(name, encoding);
      } catch (UnsupportedCharsetException ex) {
        try {
          return name.getBytes(encoding);
        } catch (UnsupportedEncodingException uee) {
          throw new ZipException(uee.getMessage());
        }
      }
    }
  }
}
