class PlaceHold {
  public void setRecentDateFormatConfig(String recentDateFormat) {
    if ((recentDateFormat != null) && (!recentDateFormat.equals(""))) {
      this.recentDateFormatConfig = recentDateFormat;
      configurationHasBeenSet();
    }
  }
}
