class PlaceHold {
  void resizeToPreferredWidth(int index) {
    int count = OS.SendMessage(handle, RB_GETBANDCOUNT, 0, 0);
    if ((0 <= index) && (index < count)) {
      REBARBANDINFO rbBand = new REBARBANDINFO();
      rbBand.cbSize = REBARBANDINFO.sizeof;
      rbBand.fMask = OS.RBBIM_IDEALSIZE;
      OS.SendMessage(handle, RB_GETBANDINFO, index, rbBand);
      RECT rect = new RECT();
      OS.SendMessage(handle, RB_GETBANDBORDERS, index, rect);
      rbBand.cx = (rbBand.cxIdeal + rect.left) + rect.right;
      rbBand.fMask = OS.RBBIM_SIZE;
      OS.SendMessage(handle, RB_SETBANDINFO, index, rbBand);
    }
  }
}
