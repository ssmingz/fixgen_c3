class PlaceHold {
  String getFormattedString(int style) {
    if ((style & SWT.TIME) != 0) {
      int h = calendar.get(Calendar.HOUR);
      int m = calendar.get(Calendar.MINUTE);
      int s = calendar.get(Calendar.SECOND);
      int a = calendar.get(Calendar.AM_PM);
      return ((((((((("" + (h < 10 ? " " : "")) + h) + ":") + (m < 10 ? " " : "")) + m) + ":")
                      + (s < 10 ? " " : ""))
                  + s)
              + " ")
          + (a == Calendar.AM ? "AM" : "PM");
    }
    int y = calendar.get(Calendar.YEAR);
    int m = calendar.get(Calendar.MONTH);
    int d = calendar.get(Calendar.DAY_OF_MONTH);
    return (((((("" + (m < 10 ? " " : "")) + m) + "/") + (d < 10 ? " " : "")) + d) + "/") + y;
  }
}
