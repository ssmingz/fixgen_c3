class PlaceHold {
  void createExampleWidgets() {
    int style = getDefaultStyle();
    if (horizontalButton.getSelection()) {
      style |= SWT.H_SCROLL;
    }
    if (verticalButton.getSelection()) {
      style |= SWT.V_SCROLL;
    }
    if (smoothButton.getSelection()) {
      style |= SWT.SMOOTH;
    }
    form = new SashForm(sashFormGroup, style);
    list1 = new List(form, (SWT.V_SCROLL | SWT.H_SCROLL) | SWT.BORDER);
    list1.setItems(ListData0);
    list2 = new List(form, (SWT.V_SCROLL | SWT.H_SCROLL) | SWT.BORDER);
    list2.setItems(ListData1);
    text = new Text(form, SWT.MULTI | SWT.BORDER);
    text.setText(ControlExample.getResourceString("Multi_line"));
    form.setWeights(new int[] {1, 1, 1});
  }
}
