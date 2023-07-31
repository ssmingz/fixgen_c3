class PlaceHold {
  protected void writeJar(String baseName, File jarFile, Hashtable files, String publicId)
      throws TaskException {
    if (ejbdeploy) {
      File genericJarFile = super.getVendorOutputJarFile(baseName);
      super.writeJar(baseName, genericJarFile, files, publicId);
      if (alwaysRebuild || isRebuildRequired(genericJarFile, jarFile)) {
        buildWebsphereJar(genericJarFile, jarFile);
      }
      if (!keepGeneric) {
        log("deleting generic jar " + genericJarFile.toString(), MSG_VERBOSE);
        genericJarFile.delete();
      }
    } else {
      super.writeJar(baseName, jarFile, files, publicId);
    }
  }
}
