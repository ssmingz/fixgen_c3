class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    if ((!checkHTML(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    String string = ((String) (object));
    int count = string.length();
    char[] chars = new char[count + 1];
    string.getChars(0, count, chars, 0);
    int cchMultiByte = OS.WideCharToMultiByte(CP_UTF8, 0, chars, -1, null, 0, null, null);
    if (cchMultiByte == 0) {
      transferData.stgmedium = new STGMEDIUM();
      transferData.result = COM.DV_E_STGMEDIUM;
      return;
    }
    int startHTML = HEADER.length();
    int startFragment = startHTML + PREFIX.length();
    int endFragment = (startFragment + cchMultiByte) - 1;
    int endHTML = endFragment + SUFFIX.length();
    StringBuffer buffer = new StringBuffer(HEADER);
    int maxLength = NUMBER.length();
    int start = buffer.toString().indexOf(NUMBER);
    String temp = Integer.toString(startHTML);
    buffer.replace((start + maxLength) - temp.length(), start + maxLength, temp);
    start = buffer.toString().indexOf(NUMBER, start);
    temp = Integer.toString(endHTML);
    buffer.replace((start + maxLength) - temp.length(), start + maxLength, temp);
    start = buffer.toString().indexOf(NUMBER, start);
    temp = Integer.toString(startFragment);
    buffer.replace((start + maxLength) - temp.length(), start + maxLength, temp);
    start = buffer.toString().indexOf(NUMBER, start);
    temp = Integer.toString(endFragment);
    buffer.replace((start + maxLength) - temp.length(), start + maxLength, temp);
    buffer.append(PREFIX);
    buffer.append(string);
    buffer.append(SUFFIX);
    count = buffer.length();
    chars = new char[count + 1];
    buffer.getChars(0, count, chars, 0);
    cchMultiByte = OS.WideCharToMultiByte(CP_UTF8, 0, chars, -1, null, 0, null, null);
    int lpMultiByteStr = OS.GlobalAlloc(OS.GMEM_FIXED | OS.GMEM_ZEROINIT, cchMultiByte);
    OS.WideCharToMultiByte(CP_UTF8, 0, chars, -1, lpMultiByteStr, cchMultiByte, null, null);
    transferData.stgmedium = new STGMEDIUM();
    transferData.stgmedium.tymed = COM.TYMED_HGLOBAL;
    transferData.stgmedium.unionField = lpMultiByteStr;
    transferData.stgmedium.pUnkForRelease = 0;
    transferData.result = COM.S_OK;
    return;
  }
}
