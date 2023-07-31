class PlaceHold {
  public void setServerLanguageCodeConfig(String serverLanguageCode) {
    if ((serverLanguageCode != null) && (!serverLanguageCode.equals(""))) {
      this.serverLanguageCodeConfig = serverLanguageCode;
      configurationHasBeenSet();
    }
  }
}
