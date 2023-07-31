class PlaceHold {
  protected String getString(byte[] bytes, String enc) throws ZipException {
    if (enc == null) {
      return new String(bytes);
    } else {
      try {
        return new String(bytes, enc);
      } catch (UnsupportedEncodingException uee) {
        throw new ZipException(uee.getMessage());
      }
    }
  }
}
