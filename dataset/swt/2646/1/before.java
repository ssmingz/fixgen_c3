class PlaceHold {
  public Object nativeToJava(TransferData transferData) {
    if ((!isSupportedType(transferData)) || (transferData.pIDataObject == 0)) {
      return null;
    }
    IDataObject data = new IDataObject(transferData.pIDataObject);
    data.AddRef();
    STGMEDIUM stgmedium = new STGMEDIUM();
    FORMATETC formatetc = transferData.formatetc;
    stgmedium.tymed = COM.TYMED_HGLOBAL;
    transferData.result = data.GetData(formatetc, stgmedium);
    data.Release();
    if (transferData.result != COM.S_OK) {
      return null;
    }
    int hMem = stgmedium.unionField;
    try {
      int lpMultiByteStr = OS.GlobalLock(hMem);
      if (lpMultiByteStr == 0) {
        return null;
      }
      try {
        int cchWideChar =
            OS.MultiByteToWideChar(CodePage, MB_PRECOMPOSED, lpMultiByteStr, -1, null, 0);
        if (cchWideChar == 0) {
          return null;
        }
        char[] lpWideCharStr = new char[cchWideChar - 1];
        OS.MultiByteToWideChar(
            CodePage, MB_PRECOMPOSED, lpMultiByteStr, -1, lpWideCharStr, lpWideCharStr.length);
        return new String(lpWideCharStr);
      } finally {
        OS.GlobalUnlock(hMem);
      }
    } finally {
      OS.GlobalFree(hMem);
    }
  }
}
