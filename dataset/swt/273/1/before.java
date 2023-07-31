class OleClientSite {
  public OleClientSite(Composite parent, int style, String progId, File file) {
    this(parent, style);
    try {
      if (((file == null) || file.isDirectory()) || (!file.exists())) {
        OLE.error(ERROR_INVALID_ARGUMENT);
      }
      appClsid = getClassID(progId);
      if (appClsid == null) {
        OLE.error(ERROR_INVALID_CLASSID);
      }
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
        IStorage storage = null;
        if (COM.StgIsStorageFile(fileName) == COM.S_OK) {
          int[] address = new int[1];
          int mode = (COM.STGM_READ | COM.STGM_TRANSACTED) | COM.STGM_SHARE_EXCLUSIVE;
          int result = COM.StgOpenStorage(fileName, 0, mode, 0, 0, address);
          if (result != COM.S_OK) {
            OLE.error(ERROR_CANNOT_OPEN_FILE, result);
          }
          storage = new IStorage(address[0]);
        } else {
          int[] address = new int[1];
          int mode =
              ((COM.STGM_READWRITE | COM.STGM_DIRECT) | COM.STGM_SHARE_EXCLUSIVE) | COM.STGM_CREATE;
          int result = COM.StgCreateDocfile(null, mode | COM.STGM_DELETEONRELEASE, 0, address);
          if (result != COM.S_OK) {
            OLE.error(ERROR_CANNOT_OPEN_FILE, result);
          }
          storage = new IStorage(address[0]);
          String streamName = "CONTENTS";
          GUID wordGUID = getClassID(WORDPROGID);
          if ((wordGUID != null) && COM.IsEqualGUID(appClsid, wordGUID)) {
            streamName = "WordDocument";
          }
          address = new int[1];
          result = storage.CreateStream(streamName, mode, 0, 0, address);
          if (result != COM.S_OK) {
            storage.Release();
            OLE.error(ERROR_CANNOT_OPEN_FILE, result);
          }
          IStream stream = new IStream(address[0]);
          try {
            FileInputStream fileInput = new FileInputStream(file);
            int increment = 1024 * 4;
            byte[] buffer = new byte[increment];
            int count = 0;
            while ((count = fileInput.read(buffer)) > 0) {
              int pv = COM.CoTaskMemAlloc(count);
              OS.MoveMemory(pv, buffer, count);
              result = stream.Write(pv, count, null);
              COM.CoTaskMemFree(pv);
              if (result != COM.S_OK) {
                fileInput.close();
                stream.Release();
                storage.Release();
                OLE.error(ERROR_CANNOT_OPEN_FILE, result);
              }
            }
            fileInput.close();
            stream.Commit(STGC_DEFAULT);
            stream.Release();
          } catch (IOException err) {
            stream.Release();
            storage.Release();
            OLE.error(ERROR_CANNOT_OPEN_FILE);
          }
        }
        tempStorage = createTempStorage();
        int result = storage.CopyTo(0, null, null, tempStorage.getAddress());
        storage.Release();
        if (result != COM.S_OK) {
          OLE.error(ERROR_CANNOT_OPEN_FILE, result);
        }
        int[] ppv = new int[1];
        result =
            COM.CoCreateInstance(
                appClsid,
                0,
                COM.CLSCTX_INPROC_HANDLER | COM.CLSCTX_INPROC_SERVER,
                IIDIUnknown,
                ppv);
        if (result != COM.S_OK) {
          OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
        }
        objIUnknown = new IUnknown(ppv[0]);
        ppv = new int[1];
        result = objIUnknown.QueryInterface(IIDIPersistStorage, ppv);
        if (result != COM.S_OK) {
          OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
        }
        IPersistStorage iPersistStorage = new IPersistStorage(ppv[0]);
        result = iPersistStorage.Load(tempStorage.getAddress());
        iPersistStorage.Release();
        if (result != COM.S_OK) {
          OLE.error(ERROR_CANNOT_CREATE_OBJECT, result);
        }
      }
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
