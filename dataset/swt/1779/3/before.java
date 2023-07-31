class PlaceHold {
  public static void main(String[] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new FillLayout());
    OleControlSite controlSite;
    try {
      OleFrame frame = new OleFrame(shell, SWT.NONE);
      controlSite = new OleControlSite(frame, SWT.NONE, "Shell.Explorer");
      controlSite.doVerb(OLEIVERB_INPLACEACTIVATE);
    } catch (SWTError e) {
      System.out.println("Unable to open activeX control");
      return;
    }
    shell.open();
    final OleAutomation webBrowser = new OleAutomation(controlSite);
    int DownloadComplete = 104;
    controlSite.addEventListener(
        DownloadComplete,
        new OleListener() {
          public void handleEvent(OleEvent event) {
            int[] htmlDocumentID = webBrowser.getIDsOfNames(new String[] {"Document"});
            if (htmlDocumentID == null) {
              return;
            }
            Variant pVarResult = webBrowser.getProperty(htmlDocumentID[0]);
            if ((pVarResult == null) || (pVarResult.getType() == 0)) {
              return;
            }
            OleAutomation htmlDocument = pVarResult.getAutomation();
            EventDispatch myDispatch = new EventDispatch(EventDispatch.onclick);
            IDispatch idispatch = new IDispatch(myDispatch.getAddress());
            Variant dispatch = new Variant(idispatch);
            htmlDocument.setProperty(EventDispatch.onclick, dispatch);
            myDispatch = new EventDispatch(EventDispatch.ondblclick);
            idispatch = new IDispatch(myDispatch.getAddress());
            dispatch = new Variant(idispatch);
            htmlDocument.setProperty(EventDispatch.ondblclick, dispatch);
            myDispatch = new EventDispatch(EventDispatch.onkeydown);
            idispatch = new IDispatch(myDispatch.getAddress());
            dispatch = new Variant(idispatch);
            htmlDocument.setProperty(EventDispatch.onkeydown, dispatch);
            htmlDocument.dispose();
          }
        });
    int[] ids = webBrowser.getIDsOfNames(new String[] {"Navigate", "URL"});
    Variant[] rgvarg = new Variant[] {new Variant("http://www.google.com")};
    int[] rgdispidNamedArgs = new int[] {ids[1]};
    webBrowser.invoke(ids[0], rgvarg, rgdispidNamedArgs);
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    webBrowser.dispose();
    display.dispose();
  }
}
