class PlaceHold {
  void hookDOMListeners(OleAutomation webBrowser, final boolean isTop) {
    int[] rgdispid = webBrowser.getIDsOfNames(new String[] {PROPERTY_DOCUMENT});
    int dispIdMember = rgdispid[0];
    Variant pVarResult = webBrowser.getProperty(dispIdMember);
    if (pVarResult == null) {
      return;
    }
    if (pVarResult.getType() == COM.VT_EMPTY) {
      pVarResult.dispose();
      return;
    }
    final OleAutomation document = pVarResult.getAutomation();
    pVarResult.dispose();
    unhookDOMListeners(new OleAutomation[] {document});
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONKEYDOWN, domListener);
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONKEYPRESS, domListener);
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONKEYUP, domListener);
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONMOUSEDOWN, domListener);
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONMOUSEUP, domListener);
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONMOUSEWHEEL, domListener);
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONDBLCLICK, domListener);
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONMOUSEMOVE, domListener);
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONDRAGSTART, domListener);
    site.addEventListener(
        document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONDRAGEND, domListener);
    if (isTop) {
      site.addEventListener(
          document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONMOUSEOVER, domListener);
      site.addEventListener(
          document, IIDIHTMLDocumentEvents2, DISPID_HTMLDOCUMENTEVENTS_ONMOUSEOUT, domListener);
    }
    OleAutomation[] newDocuments = new OleAutomation[documents.length + 1];
    System.arraycopy(documents, 0, newDocuments, 0, documents.length);
    newDocuments[documents.length] = document;
    documents = newDocuments;
  }
}
