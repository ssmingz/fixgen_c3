class PlaceHold {
  public Object getContents(Transfer transfer) {
    if (display.isDisposed()) {
      return null;
    }
    Object result = null;
    int ig = OS.PhInputGroup(0);
    int cbdata = OS.PhClipboardPasteStart(((short) (ig)));
    if (cbdata == 0) {
      return result;
    }
    try {
      String[] types = transfer.getTypeNames();
      int[] ids = transfer.getTypeIds();
      for (int i = 0; i < types.length; i++) {
        byte[] type = Converter.wcsToMbcs(null, types[i], false);
        int pClipHeader = OS.PhClipboardPasteType(cbdata, type);
        if (pClipHeader != 0) {
          PhClipHeader clipHeader = new PhClipHeader();
          OS.memmove(clipHeader, pClipHeader, sizeof);
          TransferData data = new TransferData();
          data.pData = clipHeader.data;
          data.length = clipHeader.length;
          data.type = ids[i];
          result = transfer.nativeToJava(data);
          break;
        }
      }
    } finally {
      OS.PhClipboardPasteFinish(cbdata);
    }
    return result;
  }
}
