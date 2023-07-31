class PlaceHold {
  public Object nativeToJava(TransferData transferData) {
    if ((!isSupportedType(transferData)) || (transferData.pIDataObject == 0)) {
      return null;
    }
    IDataObject data = new IDataObject(transferData.pIDataObject);
    data.AddRef();
    FORMATETC formatetc = transferData.formatetc;
    STGMEDIUM stgmedium = new STGMEDIUM();
    stgmedium.tymed = COM.TYMED_HGLOBAL;
    transferData.result = data.GetData(formatetc, stgmedium);
    data.Release();
    if (transferData.result != COM.S_OK) {
      return null;
    }
    int hMem = stgmedium.unionField;
    try {
      switch (transferData.type) {
        case CF_UNICODETEXTID:
          {
            int size = (OS.GlobalSize(hMem) / 2) * 2;
            if (size == 0) {
              return null;
            }
            char[] chars = new char[size / 2];
            int ptr = OS.GlobalLock(hMem);
            if (ptr == 0) {
              return null;
            }
            try {
              OS.MoveMemory(chars, ptr, size);
              int length = chars.length;
              for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '\u0000') {
                  length = i;
                  break;
                }
              }
              return new String(chars, 0, length);
            } finally {
              OS.GlobalUnlock(hMem);
            }
          }
        case CF_TEXTID:
          {
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
                  CodePage,
                  MB_PRECOMPOSED,
                  lpMultiByteStr,
                  -1,
                  lpWideCharStr,
                  lpWideCharStr.length);
              return new String(lpWideCharStr);
            } finally {
              OS.GlobalUnlock(hMem);
            }
          }
      }
    } finally {
      OS.GlobalFree(hMem);
    }
    return null;
  }
}
