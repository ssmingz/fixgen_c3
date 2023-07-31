class PlaceHold {
  void createHandle() {
    NSDatePicker widget = ((NSDatePicker) (new SWTDatePicker().alloc()));
    widget.init();
    int pickerStyle = OS.NSTextFieldAndStepperDatePickerStyle;
    int elementFlags = 0;
    if ((style & SWT.CALENDAR) != 0) {
      pickerStyle = OS.NSClockAndCalendarDatePickerStyle;
      elementFlags = OS.NSYearMonthDayDatePickerElementFlag;
    } else {
      if ((style & SWT.TIME) != 0) {
        elementFlags =
            ((style & SWT.SHORT) != 0)
                ? OS.NSHourMinuteDatePickerElementFlag
                : OS.NSHourMinuteSecondDatePickerElementFlag;
      }
      if ((style & SWT.DATE) != 0) {
        elementFlags =
            ((style & SWT.SHORT) != 0)
                ? OS.NSYearMonthDatePickerElementFlag
                : OS.NSYearMonthDayDatePickerElementFlag;
      }
    }
    widget.setDrawsBackground(true);
    widget.setDatePickerStyle(pickerStyle);
    widget.setDatePickerElements(elementFlags);
    NSDate date = NSCalendarDate.calendarDate();
    widget.setDateValue(date);
    widget.setTarget(widget);
    widget.setAction(sel_sendSelection);
    view = widget;
  }
}
