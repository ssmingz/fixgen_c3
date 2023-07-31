class PlaceHold {
  File getVendorOutputJarFile(String baseName) {
    File jarFile = new File(getDestDir(), baseName + jarSuffix);
    log("JAR file name: " + jarFile.toString(), MSG_VERBOSE);
    return jarFile;
  }
}
