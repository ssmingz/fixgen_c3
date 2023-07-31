class PlaceHold {
  protected void xmlSaveProperties(Properties props, OutputStream os) throws IOException {
    Document doc = getDocumentBuilder().newDocument();
    Element rootElement = doc.createElement(PROPERTIES);
    List sorted = sortProperties(props);
    Iterator iten = sorted.iterator();
    while (iten.hasNext()) {
      Tuple tuple = ((Tuple) (iten.next()));
      Element propElement = doc.createElement(PROPERTY);
      propElement.setAttribute(ATTR_NAME, tuple.key);
      propElement.setAttribute(ATTR_VALUE, tuple.value);
      rootElement.appendChild(propElement);
    }
    Writer wri = null;
    try {
      wri = new OutputStreamWriter(os, "UTF8");
      wri.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      new DOMElementWriter().write(rootElement, wri, 0, "\t");
      wri.flush();
    } catch (IOException ioe) {
      throw new BuildException("Unable to write XML file", ioe);
    } finally {
      FileUtils.close(wri);
    }
  }
}
