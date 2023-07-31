class OleClientSite {
  public OleClientSite(Composite parent, int style, File file) {
    this(parent, style);
    try {
      if (((file == null) || file.isDirectory()) || (!file.exists())) {
        OLE.error(ERROR_INVALID_ARGUMENT);
      }
      appClsid = new GUID();
      char[] fileName = (file.getAbsolutePath() + "\u0000").toCharArray();
      int result = COM.GetClassFile(fileName, appClsid);
      if (result != COM.S_OK) {
        OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
      }
      tempStorage = createTempStorage();
      int[] address = new int[1];
      result =
          COM.OleCreateFromFile(
              appClsid,
              fileName,
              IIDIUnknown,
              OLERENDER_DRAW,
              null,
              0,
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
      disposeCOMInterfaces();
      frame.Release();
      throw e;
    }
  }
}
