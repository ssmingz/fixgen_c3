class PlaceHold {
  private int EnumFormatEtc(int dwDirection, int ppenumFormatetc) {
    if (dwDirection == COM.DATADIR_SET) {
      return COM.E_NOTIMPL;
    }
    TransferData[] allowedDataTypes = new TransferData[0];
    for (int i = 0; i < transferAgents.length; i++) {
      Transfer transferAgent = transferAgents[i];
      if (transferAgent != null) {
        TransferData[] formats = transferAgent.getSupportedTypes();
        TransferData[] newAllowedDataTypes =
            new TransferData[allowedDataTypes.length + formats.length];
        System.arraycopy(allowedDataTypes, 0, newAllowedDataTypes, 0, allowedDataTypes.length);
        System.arraycopy(formats, 0, newAllowedDataTypes, allowedDataTypes.length, formats.length);
        allowedDataTypes = newAllowedDataTypes;
      }
    }
    OleEnumFORMATETC enumFORMATETC = new OleEnumFORMATETC();
    enumFORMATETC.AddRef();
    FORMATETC[] formats = new FORMATETC[allowedDataTypes.length];
    for (int i = 0; i < formats.length; i++) {
      formats[i] = allowedDataTypes[i].formatetc;
    }
    enumFORMATETC.setFormats(formats);
    OS.MoveMemory(ppenumFormatetc, new int[] {enumFORMATETC.getAddress()}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
