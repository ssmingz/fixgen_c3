class PlaceHold {
  void setHTML(String string) {
    int charCount = string.length();
    char[] chars = new char[charCount];
    string.getChars(0, charCount, chars, 0);
    int byteCount = OS.WideCharToMultiByte(CP_UTF8, 0, chars, charCount, null, 0, null, null);
    byte[] UTF8BOM = new byte[] {((byte) (0xef)), ((byte) (0xbb)), ((byte) (0xbf))};
    int hGlobal = OS.GlobalAlloc(OS.GMEM_FIXED | OS.GMEM_ZEROINIT, UTF8BOM.length + byteCount);
    if (hGlobal != 0) {
      OS.MoveMemory(hGlobal, UTF8BOM, UTF8BOM.length);
      OS.WideCharToMultiByte(
          CP_UTF8, 0, chars, charCount, hGlobal + UTF8BOM.length, byteCount, null, null);
      int[] ppstm = new int[1];
      if (OS.CreateStreamOnHGlobal(hGlobal, true, ppstm) == OS.S_OK) {
        int[] rgdispid = auto.getIDsOfNames(new String[] {PROPERTY_DOCUMENT});
        Variant pVarResult = auto.getProperty(rgdispid[0]);
        IDispatch dispatchDocument = pVarResult.getDispatch();
        int[] ppvObject = new int[1];
        int result = dispatchDocument.QueryInterface(IIDIPersistStreamInit, ppvObject);
        if (result == OS.S_OK) {
          IPersistStreamInit persistStreamInit = new IPersistStreamInit(ppvObject[0]);
          if (persistStreamInit.InitNew() == OS.S_OK) {
            persistStreamInit.Load(ppstm[0]);
          }
          persistStreamInit.Release();
        }
        pVarResult.dispose();
        IUnknown stream = new IUnknown(ppstm[0]);
        stream.Release();
      } else {
        OS.GlobalFree(hGlobal);
      }
    }
  }
}
