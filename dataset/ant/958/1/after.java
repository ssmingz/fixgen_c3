class PlaceHold {
  protected void definePackage(File container, String packageName, Manifest manifest) {
    String sectionName = packageName.replace('.', '/') + "/";
    String specificationTitle = null;
    String specificationVendor = null;
    String specificationVersion = null;
    String implementationTitle = null;
    String implementationVendor = null;
    String implementationVersion = null;
    String sealedString = null;
    URL sealBase = null;
    Attributes sectionAttributes = manifest.getAttributes(sectionName);
    if (sectionAttributes != null) {
      specificationTitle = sectionAttributes.getValue(Name.SPECIFICATION_TITLE);
      specificationVendor = sectionAttributes.getValue(Name.SPECIFICATION_VENDOR);
      specificationVersion = sectionAttributes.getValue(Name.SPECIFICATION_VERSION);
      implementationTitle = sectionAttributes.getValue(Name.IMPLEMENTATION_TITLE);
      implementationVendor = sectionAttributes.getValue(Name.IMPLEMENTATION_VENDOR);
      implementationVersion = sectionAttributes.getValue(Name.IMPLEMENTATION_VERSION);
      sealedString = sectionAttributes.getValue(Name.SEALED);
    }
    Attributes mainAttributes = manifest.getMainAttributes();
    if (mainAttributes != null) {
      if (specificationTitle == null) {
        specificationTitle = mainAttributes.getValue(Name.SPECIFICATION_TITLE);
      }
      if (specificationVendor == null) {
        specificationVendor = mainAttributes.getValue(Name.SPECIFICATION_VENDOR);
      }
      if (specificationVersion == null) {
        specificationVersion = mainAttributes.getValue(Name.SPECIFICATION_VERSION);
      }
      if (implementationTitle == null) {
        implementationTitle = mainAttributes.getValue(Name.IMPLEMENTATION_TITLE);
      }
      if (implementationVendor == null) {
        implementationVendor = mainAttributes.getValue(Name.IMPLEMENTATION_VENDOR);
      }
      if (implementationVersion == null) {
        implementationVersion = mainAttributes.getValue(Name.IMPLEMENTATION_VERSION);
      }
      if (sealedString == null) {
        sealedString = mainAttributes.getValue(Name.SEALED);
      }
    }
    if ((sealedString != null) && sealedString.toLowerCase(Locale.ENGLISH).equals("true")) {
      try {
        sealBase = new URL(FileUtils.getFileUtils().toURI(container.getAbsolutePath()));
      } catch (MalformedURLException e) {
      }
    }
    definePackage(
        packageName,
        specificationTitle,
        specificationVersion,
        specificationVendor,
        implementationTitle,
        implementationVersion,
        implementationVendor,
        sealBase);
  }
}
