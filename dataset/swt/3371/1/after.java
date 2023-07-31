class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    if ((!checkMyType(object)) || (!isSupportedType(transferData))) {
      DND.error(ERROR_INVALID_DATA);
    }
    MyType[] myTypes = ((MyType[]) (object));
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      DataOutputStream writeOut = new DataOutputStream(out);
      for (int i = 0, length = myTypes.length; i < length; i++) {
        byte[] buffer = myTypes[i].fileName.getBytes();
        writeOut.writeInt(buffer.length);
        writeOut.write(buffer);
        writeOut.writeLong(myTypes[i].fileLength);
        writeOut.writeLong(myTypes[i].lastModified);
      }
      byte[] buffer = out.toByteArray();
      writeOut.close();
      super.javaToNative(buffer, transferData);
    } catch (IOException e) {
    }
  }
}
