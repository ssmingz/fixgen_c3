class PlaceHold {
  int get_appName(int pbstrName) {
    String appName = Display.getAppName();
    if ((appName == null) || (appName.length() == 0)) {
      setString(pbstrName, null);
      return COM.S_FALSE;
    }
    setString(pbstrName, appName);
    return COM.S_OK;
  }
}
