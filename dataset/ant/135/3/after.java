class PlaceHold {
  void execute() throws Exception {
    TransformerFactory tfactory = TransformerFactory.newInstance();
    String system_id = caller.getStylesheetSystemId();
    Source xsl_src = new StreamSource(system_id);
    Transformer tformer = tfactory.newTransformer(xsl_src);
    Source xml_src = new DOMSource(caller.getDocument());
    OutputStream os = getOutputStream();
    tformer.setParameter("output.dir", caller.getToDir().getAbsolutePath());
    Result result = new StreamResult(os);
    tformer.transform(xml_src, result);
  }
}
