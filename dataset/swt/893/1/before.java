class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    if ((!checkFile(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    String[] fileNames = ((String[]) (object));
    StringBuffer allFiles = new StringBuffer();
    for (int i = 0; i < fileNames.length; i++) {
      allFiles.append(fileNames[i]);
      allFiles.append(CF_HDROP_SEPARATOR);
    }
    TCHAR buffer = new TCHAR(0, allFiles.toString(), true);
    DROPFILES dropfiles = new DROPFILES();
    dropfiles.pFiles = DROPFILES.sizeof;
    dropfiles.pt_x = dropfiles.pt_y = 0;
    dropfiles.fNC = 0;
    dropfiles.fWide = (OS.IsUnicode) ? 1 : 0;
    int byteCount = buffer.length() * TCHAR.sizeof;
    int newPtr = OS.GlobalAlloc(COM.GMEM_FIXED | COM.GMEM_ZEROINIT, DROPFILES.sizeof + byteCount);
    OS.MoveMemory(newPtr, dropfiles, sizeof);
    OS.MoveMemory(newPtr + DROPFILES.sizeof, buffer, byteCount);
    transferData.stgmedium = new STGMEDIUM();
    transferData.stgmedium.tymed = COM.TYMED_HGLOBAL;
    transferData.stgmedium.unionField = newPtr;
    transferData.stgmedium.pUnkForRelease = 0;
    transferData.result = COM.S_OK;
  }
}
