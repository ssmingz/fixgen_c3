class PlaceHold {
  public String getSchemaLocationURL() {
    boolean hasFile = file != null;
    boolean hasURL = isSet(url);
    if ((!hasFile) && (!hasURL)) {
      throw new BuildException(ERROR_NO_LOCATION + namespace);
    }
    if (hasFile && hasURL) {
      throw new BuildException(ERROR_TWO_LOCATIONS + namespace);
    }
    String schema = url;
    if (hasFile) {
      if (!file.exists()) {
        throw new BuildException(ERROR_NO_FILE + file);
      }
      try {
        schema = file.toURL().toString();
      } catch (MalformedURLException e) {
        throw new BuildException(ERROR_NO_URL_REPRESENTATION + file, e);
      }
    }
    return schema;
  }
}
