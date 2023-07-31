class PlaceHold {
  private String createDigestString(byte[] fileDigest) {
    StringBuffer checksumSb = new StringBuffer();
    for (int i = 0; i < fileDigest.length; i++) {
      String hexStr = Integer.toHexString(0xff & fileDigest[i]);
      if (hexStr.length() < 2) {
        checksumSb.append("0");
      }
      checksumSb.append(hexStr);
    }
    return checksumSb.toString();
  }
}
