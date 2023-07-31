class PlaceHold {
  public void setContents(Object[] data, Transfer[] transferAgents) {
    if (display.isDisposed()) {
      return;
    }
    if (data == null) {
      int ig = OS.PhInputGroup(0);
      if (OS.PhClipboardCopy(((short) (ig)), 0, null) != 0) {
        DND.error(ERROR_CANNOT_SET_CLIPBOARD);
      }
      return;
    }
    if ((transferAgents == null) || (data.length != transferAgents.length)) {
      DND.error(ERROR_INVALID_ARGUMENT);
    }
    byte[] clips = new byte[0];
    int count = 0;
    for (int i = 0; i < transferAgents.length; i++) {
      String[] names = transferAgents[i].getTypeNames();
      int[] ids = transferAgents[i].getTypeIds();
      for (int j = 0; j < names.length; j++) {
        TransferData transferData = new TransferData();
        transferData.type = ids[j];
        transferAgents[i].javaToNative(data[i], transferData);
        PhClipHeader clip = new PhClipHeader();
        clip.data = transferData.pData;
        clip.length = ((short) (transferData.length));
        byte[] temp = Converter.wcsToMbcs(null, names[j], true);
        byte[] type = new byte[8];
        System.arraycopy(temp, 0, type, 0, Math.min(type.length, temp.length));
        clip.type_0 = type[0];
        clip.type_1 = type[1];
        clip.type_2 = type[2];
        clip.type_3 = type[3];
        clip.type_4 = type[4];
        clip.type_5 = type[5];
        clip.type_6 = type[6];
        clip.type_7 = type[7];
        byte[] buffer = new byte[PhClipHeader.sizeof];
        OS.memmove(buffer, clip, sizeof);
        byte[] newClips = new byte[clips.length + buffer.length];
        System.arraycopy(clips, 0, newClips, 0, clips.length);
        System.arraycopy(buffer, 0, newClips, clips.length, buffer.length);
        clips = newClips;
        count++;
      }
    }
    if (count > 0) {
      int ig = OS.PhInputGroup(0);
      if (OS.PhClipboardCopy(((short) (ig)), count, clips) != 0) {
        DND.error(ERROR_CANNOT_SET_CLIPBOARD);
      }
    }
  }
}
