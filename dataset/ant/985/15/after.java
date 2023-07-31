class PlaceHold {
  File getVendorOutputJarFile(String baseName) {
    File jarFile = new File(getDestDir(), baseName + jarSuffix);
    getLogger().debug("JAR file name: " + jarFile.toString());
    return jarFile;
  }
}
