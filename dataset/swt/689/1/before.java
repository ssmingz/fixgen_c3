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
              appClsid, IIDIUnknown, OLERENDER_DRAW, null, 0, tempStorage.getAddress(), address);
      if (result != COM.S_OK) {
        OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
      }
      objIUnknown = new IUnknown(address[0]);
      addObjectReferences();
      COM.OleRun(objIUnknown.getAddress());
    } catch (SWTException e) {
      disposeCOMInterfaces();
      frame.Release();
      throw e;
    }
  }
}
