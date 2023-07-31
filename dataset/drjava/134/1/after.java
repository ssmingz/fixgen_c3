class PlaceHold {
  public static String getLicense() {
    if (initLicense) {
      return LICENSE;
    }
    try {
      InputStream is = AboutDialog.class.getResourceAsStream("/edu/rice/cs/LICENSE");
      if (is != null) {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        try {
          final StringBuilder sb = new StringBuilder();
          for (String s = r.readLine(); s != null; s = r.readLine()) {
            int lastSig = s.length() - 1;
            while ((lastSig >= 0) && Character.isWhitespace(s.charAt(lastSig))) {
              lastSig--;
            }
            if (lastSig < 0) {
              sb.append("\n");
            } else {
              sb.append(s.substring(0, lastSig + 1));
              sb.append('\n');
            }
          }
          LICENSE = sb.toString();
          LICENSE = LICENSE.trim();
          if (LICENSE.length() == 0) {
            LICENSE = null;
          }
        } finally {
          is.close();
          r.close();
        }
      }
    } catch (Exception e) {
      throw new UnexpectedException(e, StringOps.getStackTrace(e));
    }
    initLicense = true;
    return LICENSE;
  }
}
