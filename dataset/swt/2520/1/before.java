class PlaceHold {
  private int SetBorderSpace(int pborderwidths) {
    RECT borderwidth = new RECT();
    if ((pborderwidths == 0) || (currentdoc == null)) {
      return COM.S_OK;
    }
    COM.MoveMemory(borderwidth, pborderwidths, sizeof);
    currentdoc.setBorderSpace(borderwidth);
    return COM.S_OK;
  }
}
