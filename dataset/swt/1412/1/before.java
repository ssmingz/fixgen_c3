class PlaceHold {
  int get_appVersion(int pbstrVersion) {
    String appVersion = Display.getAppVersion();
    if ((appVersion == null) || (appVersion.length() == 0)) {
      return COM.S_FALSE;
    }
    setString(pbstrVersion, appVersion);
    return COM.S_OK;
  }
}
