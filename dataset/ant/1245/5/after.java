class PlaceHold {
  public void setSystemTypeKey(String systemKey) {
    if ((systemKey != null) && (!systemKey.equals(""))) {
      this.systemTypeKey = systemKey;
      configurationHasBeenSet();
    }
  }
}
