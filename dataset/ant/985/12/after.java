class PlaceHold {
  protected void writeJar(String baseName, File jarFile, Hashtable files, String publicId)
      throws TaskException {
    File genericJarFile = super.getVendorOutputJarFile(baseName);
    super.writeJar(baseName, genericJarFile, files, publicId);
    if (alwaysRebuild || isRebuildRequired(genericJarFile, jarFile)) {
      buildWeblogicJar(genericJarFile, jarFile, publicId);
    }
    if (!keepGeneric) {
      getLogger().debug("deleting generic jar " + genericJarFile.toString());
      genericJarFile.delete();
    }
  }
}
