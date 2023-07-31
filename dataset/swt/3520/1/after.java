class OleControlSite {
  public OleControlSite(Composite parent, int style, String progId) {
    super(parent, style);
    try {
      appClsid = getClassID(progId);
      if (appClsid == null) {
        OLE.error(ERROR_INVALID_CLASSID);
      }
      int licinfo = getLicenseInfo(appClsid);
      if (licinfo == 0) {
        tempStorage = createTempStorage();
        int[] address = new int[1];
        int clientSite = (isICAClient()) ? 0 : iOleClientSite.getAddress();
        int result =
            COM.OleCreate(
                appClsid,
                IIDIUnknown,
                OLERENDER_DRAW,
                null,
                clientSite,
                tempStorage.getAddress(),
                address);
        if (result != COM.S_OK) {
          OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
        }
        objIUnknown = new IUnknown(address[0]);
      } else {
        int[] ppvObject = new int[1];
        try {
          int result =
              COM.CoGetClassObject(
                  appClsid,
                  COM.CLSCTX_INPROC_HANDLER | COM.CLSCTX_INPROC_SERVER,
                  0,
                  IIDIClassFactory2,
                  ppvObject);
          if (result != COM.S_OK) {
            OLE.error(ERROR_CANNOT_ACCESS_CLASSFACTORY, result);
          }
          IClassFactory2 classFactory = new IClassFactory2(ppvObject[0]);
          ppvObject = new int[1];
          result = classFactory.CreateInstanceLic(0, 0, IIDIUnknown, licinfo, ppvObject);
          classFactory.Release();
          if (result != COM.S_OK) {
            OLE.error(ERROR_CANNOT_CREATE_LICENSED_OBJECT, result);
          }
        } finally {
          COM.SysFreeString(licinfo);
        }
        objIUnknown = new IUnknown(ppvObject[0]);
        ppvObject = new int[1];
        if (objIUnknown.QueryInterface(IIDIPersistStorage, ppvObject) == COM.S_OK) {
          IPersistStorage persist = new IPersistStorage(ppvObject[0]);
          tempStorage = createTempStorage();
          persist.InitNew(tempStorage.getAddress());
          persist.Release();
        }
      }
      addObjectReferences();
      setSiteProperty(DISPID_AMBIENT_USERMODE, new Variant(true));
      setSiteProperty(DISPID_AMBIENT_UIDEAD, new Variant(false));
      if (COM.OleRun(objIUnknown.getAddress()) == OLE.S_OK) {
        state = STATE_RUNNING;
      }
    } catch (SWTError e) {
      dispose();
      disposeCOMInterfaces();
      throw e;
    }
  }
}
