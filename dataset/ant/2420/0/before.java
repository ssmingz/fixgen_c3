class PlaceHold {
  public void parse(Project project, Object source, RootHandler handler) throws BuildException {
    AntXmlContext context = handler.context;
    System.out.println("Parsing with PH2: " + source);
    if (source instanceof File) {
      context.buildFile = ((File) (source));
    } else {
      throw new BuildException(
          ("Source " + source.getClass().getName()) + " not supported by this plugin");
    }
    FileInputStream inputStream = null;
    InputSource inputSource = null;
    context.buildFile = new File(context.buildFile.getAbsolutePath());
    context.buildFileParent = new File(context.buildFile.getParent());
    try {
      context.parser = JAXPUtils.getXMLReader();
      String uri = "file:" + context.buildFile.getAbsolutePath().replace('\\', '/');
      for (int index = uri.indexOf('#'); index != (-1); index = uri.indexOf('#')) {
        uri = (uri.substring(0, index) + "%23") + uri.substring(index + 1);
      }
      inputStream = new FileInputStream(context.buildFile);
      inputSource = new InputSource(inputStream);
      inputSource.setSystemId(uri);
      project.log((("parsing buildfile " + context.buildFile) + " with URI = ") + uri, MSG_VERBOSE);
      DefaultHandler hb = handler;
      context.parser.setContentHandler(hb);
      context.parser.setEntityResolver(hb);
      context.parser.setErrorHandler(hb);
      context.parser.setDTDHandler(hb);
      context.parser.parse(inputSource);
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
