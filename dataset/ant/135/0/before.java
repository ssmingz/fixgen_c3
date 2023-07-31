class PlaceHold {
  void execute() throws Exception {
    XSLTProcessor processor = XSLTProcessorFactory.getProcessor();
    processor.setStylesheetParam("output.dir", ("'" + toDir.getAbsolutePath()) + "'");
    XSLTInputSource xml_src = new XSLTInputSource(caller.document);
    String system_id = caller.getStylesheetSystemId();
    XSLTInputSource xsl_src = new XSLTInputSource(system_id);
    OutputStream os = getOutputStream();
    XSLTResultTarget target = new XSLTResultTarget(os);
    processor.process(xml_src, xsl_src, target);
  }
}
