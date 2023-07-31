class PlaceHold {
  int get_appName(int pbstrName) {
    String appName = Display.getAppName();
    if (DEBUG) {
      print(
          ((this + ".IAccessibleApplication::get_appName() returning ") + appName)
              + hresult((appName == null) || (appName.length() == 0) ? COM.S_FALSE : COM.S_OK));
    }
    if ((appName == null) || (appName.length() == 0)) {
      setString(pbstrName, null);
      return COM.S_FALSE;
    }
    setString(pbstrName, appName);
    return COM.S_OK;
  }
}
