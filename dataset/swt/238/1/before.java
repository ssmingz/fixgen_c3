class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    if ((!_validate(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    transferData.result = COM.E_FAIL;
    String string = ((String) (object));
    switch (transferData.type) {
      case COM.CF_UNICODETEXT:
        {
          int charCount = string.length();
          char[] chars = new char[charCount + 1];
          string.getChars(0, charCount, chars, 0);
          int byteCount = chars.length * 2;
          int newPtr = OS.GlobalAlloc(COM.GMEM_FIXED | COM.GMEM_ZEROINIT, byteCount);
          OS.MoveMemory(newPtr, chars, byteCount);
          transferData.stgmedium = new STGMEDIUM();
          transferData.stgmedium.tymed = COM.TYMED_HGLOBAL;
          transferData.stgmedium.unionField = newPtr;
          transferData.stgmedium.pUnkForRelease = 0;
          transferData.result = COM.S_OK;
          break;
        }
      case COM.CF_TEXT:
        {
          int count = string.length();
          char[] chars = new char[count + 1];
          string.getChars(0, count, chars, 0);
          int cchMultiByte = OS.WideCharToMultiByte(CodePage, 0, chars, -1, null, 0, null, null);
          if (cchMultiByte == 0) {
            transferData.stgmedium = new STGMEDIUM();
            transferData.result = COM.DV_E_STGMEDIUM;
            return;
          }
          int lpMultiByteStr = OS.GlobalAlloc(COM.GMEM_FIXED | COM.GMEM_ZEROINIT, cchMultiByte);
          OS.WideCharToMultiByte(CodePage, 0, chars, -1, lpMultiByteStr, cchMultiByte, null, null);
          transferData.stgmedium = new STGMEDIUM();
          transferData.stgmedium.tymed = COM.TYMED_HGLOBAL;
          transferData.stgmedium.unionField = lpMultiByteStr;
          transferData.stgmedium.pUnkForRelease = 0;
          transferData.result = COM.S_OK;
          break;
        }
    }
    return;
  }
}
