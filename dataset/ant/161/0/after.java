class PlaceHold {
  public void buildFinished(BuildEvent event) {
    long totalTime = System.currentTimeMillis() - buildElement.startTime;
    buildElement.element.setAttribute(TIME_ATTR, DefaultLogger.formatTime(totalTime));
    if (event.getException() != null) {
      buildElement.element.setAttribute(ERROR_ATTR, event.getException().toString());
    }
    try {
      String outFilename = event.getProject().getProperty("XmlLogger.file");
      if (outFilename == null) {
        outFilename = "log.xml";
      }
      Writer out = new OutputStreamWriter(new FileOutputStream(outFilename), "UTF8");
      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
      out.write("<?xml:stylesheet type=\"text/xsl\" href=\"log.xsl\"?>\n\n");
      new DOMElementWriter().write(buildElement.element, out, 0, "\t");
      out.flush();
      out.close();
    } catch (IOException exc) {
      throw new BuildException("Unable to close log file", exc);
    }
    buildElement = null;
  }
}
