class PlaceHold {
  void sendSelectionEvent() {
    LongDateRec rec = new LongDateRec();
    OS.GetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, rec, null);
    if ((((((rec.second != dateRec.second) || (rec.minute != dateRec.minute))
                    || (rec.hour != dateRec.hour))
                || (rec.day != dateRec.day))
            || (rec.month != dateRec.month))
        || (rec.year != dateRec.year)) {
      dateRec = rec;
      sendSelectionEvent(Selection);
    }
  }
}
