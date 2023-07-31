class PlaceHold {
  public void save(OutputStream os) {
    if (isDelegated()) {
      _parent.save(os);
      return;
    }
    Source source = new DOMSource(_document);
    try {
      TransformerFactory tf = TransformerFactory.newInstance();
      tf.setAttribute("indent-number", new Integer(2));
      Transformer t = tf.newTransformer();
      t.setOutputProperty(OutputKeys.INDENT, "yes");
      t.transform(source, new StreamResult(new OutputStreamWriter(os, "utf-8")));
    } catch (TransformerException e) {
      throw new XMLConfigException("Error in save", e);
    } catch (UnsupportedEncodingException e) {
      throw new XMLConfigException("Error in save", e);
    }
  }
}
