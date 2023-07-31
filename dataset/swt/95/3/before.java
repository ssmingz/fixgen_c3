class PlaceHold {
  protected void javaToNative(Object object, TransferData transferData) {
    if ((!_validate(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    byte[] data = ((byte[]) (object));
    int size = data.length;
    int newPtr = OS.GlobalAlloc(OS.GMEM_FIXED | OS.GMEM_ZEROINIT, size);
    OS.MoveMemory(newPtr, data, size);
    transferData.stgmedium = new STGMEDIUM();
    transferData.stgmedium.tymed = COM.TYMED_HGLOBAL;
    transferData.stgmedium.unionField = newPtr;
    transferData.stgmedium.pUnkForRelease = 0;
    transferData.result = COM.S_OK;
  }
}
