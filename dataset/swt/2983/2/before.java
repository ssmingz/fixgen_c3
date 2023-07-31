class PlaceHold {
  private int EnumFormatEtc(int dwDirection, int ppenumFormatetc) {
    if (dwDirection == COM.DATADIR_SET) {
      return COM.E_NOTIMPL;
    }
    TransferData[] allowedDataTypes = new TransferData[0];
    for (int i = 0; i < transferAgents.length; i++) {
      TransferData[] formats = transferAgents[i].getSupportedTypes();
      TransferData[] newAllowedDataTypes =
          new TransferData[allowedDataTypes.length + formats.length];
      System.arraycopy(allowedDataTypes, 0, newAllowedDataTypes, 0, allowedDataTypes.length);
      System.arraycopy(formats, 0, newAllowedDataTypes, allowedDataTypes.length, formats.length);
      allowedDataTypes = newAllowedDataTypes;
    }
    OleEnumFORMATETC enumFORMATETC = new OleEnumFORMATETC();
    enumFORMATETC.AddRef();
    FORMATETC[] formats = new FORMATETC[allowedDataTypes.length + 1];
    for (int i = 0; i < allowedDataTypes.length; i++) {
      formats[i] = allowedDataTypes[i].formatetc;
    }
    FORMATETC dropeffect = new FORMATETC();
    dropeffect.cfFormat = CFSTR_PREFERREDDROPEFFECT;
    dropeffect.dwAspect = COM.DVASPECT_CONTENT;
    dropeffect.lindex = -1;
    dropeffect.tymed = COM.TYMED_HGLOBAL;
    formats[formats.length - 1] = dropeffect;
    enumFORMATETC.setFormats(formats);
    COM.MoveMemory(ppenumFormatetc, new int[] {enumFORMATETC.getAddress()}, 4);
    return COM.S_OK;
  }
}
