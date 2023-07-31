class PlaceHold {
  public static ZipExtraField[] parse(
      byte[] data, boolean local, UnparseableExtraField onUnparseableData) throws ZipException {
    List v = new ArrayList();
    int start = 0;
    LOOP:
    while (start <= (data.length - WORD)) {
      ZipShort headerId = new ZipShort(data, start);
      int length = new ZipShort(data, start + 2).getValue();
      if (((start + WORD) + length) > data.length) {
        switch (onUnparseableData.getKey()) {
          case UnparseableExtraField.THROW_KEY:
            throw new ZipException(
                (((((("bad extra field starting at " + start) + ".  Block length of ") + length)
                                + " bytes exceeds remaining")
                            + " data of ")
                        + ((data.length - start) - WORD))
                    + " bytes.");
          case UnparseableExtraField.READ_KEY:
            UnparseableExtraFieldData field = new UnparseableExtraFieldData();
            if (local) {
              field.parseFromLocalFileData(data, start, data.length - start);
            } else {
              field.parseFromCentralDirectoryData(data, start, data.length - start);
            }
            v.add(field);
          case UnparseableExtraField.SKIP_KEY:
            break LOOP;
          default:
            throw new ZipException(
                "unknown UnparseableExtraField key: " + onUnparseableData.getKey());
        }
      }
      try {
        ZipExtraField ze = createExtraField(headerId);
        if (local || (!(ze instanceof CentralDirectoryParsingZipExtraField))) {
          ze.parseFromLocalFileData(data, start + WORD, length);
        } else {
          ((CentralDirectoryParsingZipExtraField) (ze))
              .parseFromCentralDirectoryData(data, start + WORD, length);
        }
        v.add(ze);
      } catch (InstantiationException ie) {
        throw new ZipException(ie.getMessage());
      } catch (IllegalAccessException iae) {
        throw new ZipException(iae.getMessage());
      }
      start += length + WORD;
    }
    ZipExtraField[] result = new ZipExtraField[v.size()];
    return ((ZipExtraField[]) (v.toArray(result)));
  }
}
