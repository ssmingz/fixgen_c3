class PlaceHold {
  private void doValidate(File afile) {
    try {
      log(("Validating " + afile.getName()) + "... ", MSG_VERBOSE);
      errorHandler.init(afile);
      InputSource is = new InputSource(new FileInputStream(afile));
      String uri = fu.toURI(afile.getAbsolutePath());
      is.setSystemId(uri);
      xmlReader.parse(is);
    } catch (SAXException ex) {
      if (failOnError) {
        throw new BuildException("Could not validate document " + afile);
      } else {
        log((("Could not validate document " + afile) + ": ") + ex.toString());
      }
    } catch (IOException ex) {
      throw new BuildException("Could not validate document " + afile, ex);
    }
    if (errorHandler.getFailure()) {
      if (failOnError) {
        throw new BuildException(afile + " is not a valid XML document.");
      } else {
        log(afile + " is not a valid XML document", MSG_ERR);
      }
    }
  }
}
