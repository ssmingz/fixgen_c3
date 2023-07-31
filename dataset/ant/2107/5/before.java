class PlaceHold {
  public void buildFinished(BuildEvent event) {
    long totalTime = System.currentTimeMillis() - buildElement.startTime;
    buildElement.element.setAttribute(TIME_ATTR, DefaultLogger.formatTime(totalTime));
    if (event.getException() != null) {
      buildElement.element.setAttribute(ERROR_ATTR, event.getException().toString());
      Throwable t = event.getException();
      Text errText = doc.createCDATASection(StringUtils.getStackTrace(t));
      Element stacktrace = doc.createElement(STACKTRACE_TAG);
      stacktrace.appendChild(errText);
      buildElement.element.appendChild(stacktrace);
    }
    String outFilename = event.getProject().getProperty("XmlLogger.file");
    if (outFilename == null) {
      outFilename = "log.xml";
    }
    String xslUri = event.getProject().getProperty("ant.XmlLogger.stylesheet.uri");
    if (xslUri == null) {
      xslUri = "log.xsl";
    }
    Writer out = null;
    try {
      OutputStream stream = outStream;
      if (stream == null) {
        stream = new FileOutputStream(outFilename);
      }
      out = new OutputStreamWriter(stream, "UTF8");
      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      if (xslUri.length() > 0) {
        out.write(("<?xml-stylesheet type=\"text/xsl\" href=\"" + xslUri) + "\"?>\n\n");
      }
      new DOMElementWriter().write(buildElement.element, out, 0, "\t");
      out.flush();
    } catch (IOException exc) {
      throw new BuildException("Unable to write log file", exc);
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException e) {
        }
      }
    }
    buildElement = null;
  }
}
