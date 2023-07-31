class PlaceHold {
  public void javaToNative(Object object, TransferData transferData) {
    if ((object == null) || (!(object instanceof MyType[]))) {
      return;
    }
    if (isSupportedType(transferData)) {
      MyType[] myTypes = ((MyType[]) (object));
      try {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream writeOut = new DataOutputStream(out);
        for (int i = 0, length = myTypes.length; i < length; i++) {
          byte[] buffer = myTypes[i].firstName.getBytes();
          writeOut.writeInt(buffer.length);
          writeOut.write(buffer);
          buffer = myTypes[i].firstName.getBytes();
          writeOut.writeInt(buffer.length);
          writeOut.write(buffer);
        }
        byte[] buffer = out.toByteArray();
        writeOut.close();
        super.javaToNative(buffer, transferData);
      } catch (IOException e) {
      }
    }
  }
}
