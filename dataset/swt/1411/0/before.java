class PlaceHold {
  protected GUID getClassID(String clientName) {
    GUID guid = new GUID();
    char[] buffer = null;
    if (clientName != null) {
      int count = clientName.length();
      buffer = new char[count + 1];
      clientName.getChars(0, count, buffer, 0);
    }
    if (COM.CLSIDFromProgID(buffer, guid) != COM.S_OK) {
      int result = COM.CLSIDFromString(buffer, guid);
      if (result != COM.S_OK) {
        OLE.error(ERROR_INVALID_CLASSID, result);
      }
    }
    return guid;
  }
}
