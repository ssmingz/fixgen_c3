class PlaceHold {
  public void setDefaultDateFormatConfig(String defaultDateFormat) {
    if ((defaultDateFormat != null) && (!defaultDateFormat.equals(""))) {
      this.defaultDateFormatConfig = defaultDateFormat;
      configurationHasBeenSet();
    }
  }
}
