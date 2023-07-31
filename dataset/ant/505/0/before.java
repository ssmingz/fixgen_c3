class PlaceHold {
  public void setUseLanguageEncodingFlag(boolean b) {
    useEFS = b && ZipEncodingHelper.isUTF8(encoding);
  }
}
