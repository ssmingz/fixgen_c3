class PlaceHold {
  public void applyWebProxySettings() {
    boolean settingsChanged = false;
    boolean enablingProxy = false;
    Properties sysprops = System.getProperties();
    if (proxyHost != null) {
      settingsChanged = true;
      if (proxyHost.length() != 0) {
        traceSettingInfo();
        enablingProxy = true;
        sysprops.put("http.proxyHost", proxyHost);
        String portString = Integer.toString(proxyPort);
        sysprops.put("http.proxyPort", portString);
        sysprops.put("https.proxyHost", proxyHost);
        sysprops.put("https.proxyPort", portString);
        sysprops.put("ftp.proxyHost", proxyHost);
        sysprops.put("ftp.proxyPort", portString);
        if (nonProxyHosts != null) {
          sysprops.put("http.nonProxyHosts", nonProxyHosts);
          sysprops.put("ftp.nonProxyHosts", nonProxyHosts);
        }
      } else {
        log("resetting http proxy", MSG_VERBOSE);
        sysprops.remove("http.proxyPort");
        sysprops.remove("https.proxyHost");
        sysprops.remove("https.proxyPort");
        sysprops.remove("ftp.proxyHost");
        sysprops.remove("ftp.proxyPort");
      }
    }
    if (socksProxyHost != null) {
      settingsChanged = true;
      if (socksProxyHost.length() != 0) {
        enablingProxy = true;
        sysprops.put("socksProxyHost", socksProxyHost);
        sysprops.put("socksProxyPort", Integer.toString(socksProxyPort));
      } else {
        log("resetting socks proxy", MSG_VERBOSE);
        sysprops.remove("socksProxyHost");
        sysprops.remove("socksProxyPort");
      }
    }
    if (settingsChanged && JavaEnvUtils.isJavaVersion(JAVA_1_1)) {
      legacyResetProxySettingsCall(enablingProxy);
    }
  }
}
