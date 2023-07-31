class PlaceHold {
  int get_appVersion(int pbstrVersion) {
    String appVersion = Display.getAppVersion();
    if (DEBUG) {
      print(
          ((this + ".IAccessibleApplication::get_appVersion() returning") + appVersion)
              + hresult(
                  (appVersion == null) || (appVersion.length() == 0) ? COM.S_FALSE : COM.S_OK));
    }
    if ((appVersion == null) || (appVersion.length() == 0)) {
      setString(pbstrVersion, null);
      return COM.S_FALSE;
    }
    setString(pbstrVersion, appVersion);
    return COM.S_OK;
  }
}
