class PlaceHold {
  Extension toExtension() throws BuildException {
    if (null == extensionName) {
      final String message = "Extension is missing name.";
      throw new BuildException(message);
    }
    String specificationVersionString = null;
    if (null != specificationVersion) {
      specificationVersionString = specificationVersion.toString();
    }
    String implementationVersionString = null;
    if (null != implementationVersion) {
      implementationVersionString = implementationVersion.toString();
    }
    return new Extension(
        extensionName,
        specificationVersionString,
        specificationVendor,
        implementationVersionString,
        implementationVendor,
        implementationVendorID,
        implementationVendorURL);
  }
}
