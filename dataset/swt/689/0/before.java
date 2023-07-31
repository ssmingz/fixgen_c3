class OleClientSite {
  public OleClientSite(Composite parent, int style, String progId, File file) {
    this(parent, style);
    try {
      if (((file == null) || file.isDirectory()) || (!file.exists())) {
        OLE.error(ERROR_INVALID_ARGUMENT);
      }
      appClsid = getClassID(progId);
      char[] fileName = (file.getAbsolutePath() + "\u0000").toCharArray();
      GUID fileClsid = new GUID();
      COM.GetClassFile(fileName, fileClsid);
      if (COM.IsEqualGUID(appClsid, fileClsid)) {
        tempStorage = createTempStorage();
        int[] address = new int[1];
        int result =
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
      } else {
        String contentStream = "CONTENTS";
        GUID wordGUID = getClassID(WORDPROGID);
        if (COM.IsEqualGUID(appClsid, wordGUID)) {
          contentStream = "WordDocument";
        }
        OleFile oleFile = new OleFile(file, contentStream, OleFile.READ);
        IStorage storage = oleFile.getRootStorage();
        storage.AddRef();
        tempStorage = createTempStorage();
        int result = storage.CopyTo(0, null, null, tempStorage.getAddress());
        storage.Release();
        if (result != COM.S_OK) {
          OLE.error(ERROR_CANNOT_OPEN_FILE, result);
        }
        oleFile.dispose();
        int[] ppv = new int[1];
        result =
            COM.CoCreateInstance(
                appClsid,
                0,
                COM.CLSCTX_INPROC_HANDLER | COM.CLSCTX_INPROC_SERVER,
                IIDIUnknown,
                ppv);
        if (result != COM.S_OK) {
          tempStorage.Release();
          OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
        }
        objIUnknown = new IUnknown(ppv[0]);
        ppv = new int[1];
        result = objIUnknown.QueryInterface(IIDIPersistStorage, ppv);
        if (result != COM.S_OK) {
          tempStorage.Release();
          objIUnknown.Release();
          OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
        }
        IPersistStorage iPersistStorage = new IPersistStorage(ppv[0]);
        result = iPersistStorage.Load(tempStorage.getAddress());
        iPersistStorage.Release();
        if (result != COM.S_OK) {
          tempStorage.Release();
          tempStorage = null;
          objIUnknown.Release();
          objIUnknown = null;
          OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
        }
      }
      addObjectReferences();
      COM.OleRun(objIUnknown.getAddress());
    } catch (SWTException e) {
      disposeCOMInterfaces();
      frame.Release();
      throw e;
    }
  }
}
