class PlaceHold {
  public int GetNames(int memid, String[] names, int cMaxNames, int[] pcNames) {
    int nameSize = names.length;
    long[] rgBstrNames = new long[nameSize];
    int rc = COM.VtblCall(7, address, memid, rgBstrNames, nameSize, pcNames);
    if (rc == COM.S_OK) {
      for (int i = 0; i < pcNames[0]; i++) {
        int size = COM.SysStringByteLen(rgBstrNames[i]);
        if (size > 0) {
          char[] buffer = new char[(size + 1) / 2];
          COM.MoveMemory(buffer, rgBstrNames[i], size);
          names[i] = new String(buffer);
          int subindex = names[i].indexOf("\u0000");
          if (subindex > 0) {
            names[i] = names[i].substring(0, subindex);
          }
        }
        COM.SysFreeString(rgBstrNames[i]);
      }
    }
    return rc;
  }
}
