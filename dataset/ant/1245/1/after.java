class PlaceHold {
  public void setServerTimeZoneConfig(String serverTimeZoneId) {
    if ((serverTimeZoneId != null) && (!serverTimeZoneId.equals(""))) {
      this.serverTimeZoneConfig = serverTimeZoneId;
      configurationHasBeenSet();
    }
  }
}
