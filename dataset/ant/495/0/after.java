class PlaceHold {
  public synchronized void readFiltersFromFile(File filtersFile) throws BuildException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    if (!filtersFile.exists()) {
      handleMissingFile(
          ("Could not read filters from file " + filtersFile) + " as it doesn't exist.");
    }
    if (filtersFile.isFile()) {
      log("Reading filters from " + filtersFile, MSG_VERBOSE);
      FileInputStream in = null;
      try {
        Properties props = new Properties();
        in = new FileInputStream(filtersFile);
        props.load(in);
        Enumeration e = props.propertyNames();
        Vector filts = getFilters();
        while (e.hasMoreElements()) {
          String strPropName = ((String) (e.nextElement()));
          String strValue = props.getProperty(strPropName);
          filts.addElement(new Filter(strPropName, strValue));
        }
      } catch (Exception ex) {
        throw new BuildException("Could not read filters from file: " + filtersFile, ex);
      } finally {
        FileUtils.close(in);
      }
    } else {
      handleMissingFile(
          ("Must specify a file rather than a directory in " + "the filtersfile attribute:")
              + filtersFile);
    }
    filterHash = null;
  }
}
