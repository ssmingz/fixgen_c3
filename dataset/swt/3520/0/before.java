class OleClientSite {
  public OleClientSite(Composite parent, int style, String progId) {
    this(parent, style);
    try {
      appClsid = getClassID(progId);
      if (appClsid == null) {
        OLE.error(ERROR_INVALID_CLASSID);
      }
      tempStorage = createTempStorage();
      int[] address = new int[1];
      int result =
          COM.OleCreate(
              appClsid,
              IIDIUnknown,
              OLERENDER_DRAW,
              null,
              iOleClientSite.getAddress(),
              tempStorage.getAddress(),
              address);
      if (result != COM.S_OK) {
        OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
      }
      objIUnknown = new IUnknown(address[0]);
      addObjectReferences();
      if (COM.OleRun(objIUnknown.getAddress()) == OLE.S_OK) {
        state = STATE_RUNNING;
      }
    } catch (SWTException e) {
      dispose();
      disposeCOMInterfaces();
      throw e;
    }
  }
}
