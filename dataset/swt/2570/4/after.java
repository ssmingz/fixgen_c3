class PlaceHold {
  void addWidget() {
    super.addWidget();
    if ((style & SWT.READ_ONLY) == 0) {
      updateLayout(handle);
      int name = createDotNetString("PART_EditableTextBox", false);
      int template = OS.Control_Template(handle);
      textHandle = OS.FrameworkTemplate_FindName(template, name, handle);
      OS.GCHandle_Free(name);
      OS.GCHandle_Free(template);
    }
  }
}
