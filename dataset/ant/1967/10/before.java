class PlaceHold {
  protected boolean isValidRdbms(Connection conn) {
    if ((rdbms == null) && (version == null)) {
      return true;
    }
    try {
      DatabaseMetaData dmd = conn.getMetaData();
      if (rdbms != null) {
        String theVendor = dmd.getDatabaseProductName().toLowerCase();
        log("RDBMS = " + theVendor, MSG_VERBOSE);
        if ((theVendor == null) || (theVendor.indexOf(rdbms) < 0)) {
          log("Not the required RDBMS: " + rdbms, MSG_VERBOSE);
          return false;
        }
      }
      if (version != null) {
        String theVersion = dmd.getDatabaseProductVersion().toLowerCase();
        log("Version = " + theVersion, MSG_VERBOSE);
        if ((theVersion == null)
            || (!(theVersion.startsWith(version) || (theVersion.indexOf(" " + version) >= 0)))) {
          log(("Not the required version: \"" + version) + "\"", MSG_VERBOSE);
          return false;
        }
      }
    } catch (SQLException e) {
      log("Failed to obtain required RDBMS information", MSG_ERR);
      return false;
    }
    return true;
  }
}
