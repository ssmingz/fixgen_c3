class PlaceHold {
  private void initValidator() {
    Object reader = null;
    if (readerClassName == null) {
      try {
        reader = JAXPUtils.getXMLReader();
      } catch (BuildException exc) {
        reader = JAXPUtils.getParser();
      }
    } else {
      Class readerClass = null;
      try {
        if (classpath != null) {
          AntClassLoader loader = getProject().createClassLoader(classpath);
          readerClass = loader.loadClass(readerClassName);
          AntClassLoader.initializeClass(readerClass);
        } else {
          readerClass = Class.forName(readerClassName);
        }
        reader = readerClass.newInstance();
      } catch (ClassNotFoundException e) {
        throw new BuildException(INIT_FAILED_MSG + readerClassName, e);
      } catch (InstantiationException e) {
        throw new BuildException(INIT_FAILED_MSG + readerClassName, e);
      } catch (IllegalAccessException e) {
        throw new BuildException(INIT_FAILED_MSG + readerClassName, e);
      }
    }
    if (reader instanceof XMLReader) {
      xmlReader = ((XMLReader) (reader));
      log("Using SAX2 reader " + reader.getClass().getName(), MSG_VERBOSE);
    } else if (reader instanceof Parser) {
      xmlReader = new ParserAdapter(((Parser) (reader)));
      log("Using SAX1 parser " + reader.getClass().getName(), MSG_VERBOSE);
    } else {
      throw new BuildException(
          (INIT_FAILED_MSG + reader.getClass().getName())
              + " implements nor SAX1 Parser nor SAX2 XMLReader.");
    }
    xmlReader.setEntityResolver(getEntityResolver());
    xmlReader.setErrorHandler(errorHandler);
    if (!(xmlReader instanceof ParserAdapter)) {
      if (!lenient) {
        setFeature("http://xml.org/sax/features/validation", true);
      }
      for (int i = 0; i < attributeList.size(); i++) {
        Attribute feature = ((Attribute) (attributeList.elementAt(i)));
        setFeature(feature.getName(), feature.getValue());
      }
    }
  }
}
