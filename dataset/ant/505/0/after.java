class PlaceHold {
  public void setUseLanguageEncodingFlag(boolean b) {
    useUTF8Flag = b && ZipEncodingHelper.isUTF8(encoding);
  }
}
