class PlaceHold {
  public void parse(Project project, Object source) throws BuildException {
    if (!(source instanceof File)) {
      throw new BuildException("Only File source supported by default plugin");
    }
    File buildFile = ((File) (source));
    FileInputStream inputStream = null;
    InputSource inputSource = null;
    this.project = project;
    this.buildFile = new File(buildFile.getAbsolutePath());
    buildFileParent = new File(this.buildFile.getParent());
    try {
      try {
        parser = JAXPUtils.getParser();
      } catch (BuildException e) {
        parser = new XMLReaderAdapter(JAXPUtils.getXMLReader());
      }
      String uri = "file:" + buildFile.getAbsolutePath().replace('\\', '/');
      for (int index = uri.indexOf('#'); index != (-1); index = uri.indexOf('#')) {
        uri = (uri.substring(0, index) + "%23") + uri.substring(index + 1);
      }
      inputStream = new FileInputStream(buildFile);
      inputSource = new InputSource(inputStream);
      inputSource.setSystemId(uri);
      project.log((("parsing buildfile " + buildFile) + " with URI = ") + uri, MSG_VERBOSE);
      HandlerBase hb = new RootHandler(this);
      parser.setDocumentHandler(hb);
      parser.setEntityResolver(hb);
      parser.setErrorHandler(hb);
      parser.setDTDHandler(hb);
      parser.parse(inputSource);
    } catch (SAXParseException exc) {
      Location location =
          new Location(exc.getSystemId(), exc.getLineNumber(), exc.getColumnNumber());
      Throwable t = exc.getException();
      if (t instanceof BuildException) {
        BuildException be = ((BuildException) (t));
        if (be.getLocation() == Location.UNKNOWN_LOCATION) {
          be.setLocation(location);
        }
        throw be;
      }
      throw new BuildException(exc.getMessage(), t, location);
    } catch (SAXException exc) {
      Throwable t = exc.getException();
      if (t instanceof BuildException) {
        throw ((BuildException) (t));
      }
      throw new BuildException(exc.getMessage(), t);
    } catch (FileNotFoundException exc) {
      throw new BuildException(exc);
    } catch (UnsupportedEncodingException exc) {
      throw new BuildException("Encoding of project file is invalid.", exc);
    } catch (IOException exc) {
      throw new BuildException("Error reading project file: " + exc.getMessage(), exc);
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException ioe) {
        }
      }
    }
  }
}
