class PlaceHold {
  void resetEditors(boolean tab) {
    TableItem oldItem = comboEditor.getItem();
    if (oldItem != null) {
      int row = table.indexOf(oldItem);
      try {
        new Integer(widthText.getText()).intValue();
      } catch (NumberFormatException e) {
        widthText.setText(oldItem.getText(WIDTH_COL));
      }
      try {
        new Integer(heightText.getText()).intValue();
      } catch (NumberFormatException e) {
        heightText.setText(oldItem.getText(HEIGHT_COL));
      }
      try {
        new Integer(hIndent.getText()).intValue();
      } catch (NumberFormatException e) {
        hIndent.setText(oldItem.getText(HINDENT_COL));
      }
      try {
        new Integer(hSpan.getText()).intValue();
      } catch (NumberFormatException e) {
        hSpan.setText(oldItem.getText(HSPAN_COL));
      }
      try {
        new Integer(vSpan.getText()).intValue();
      } catch (NumberFormatException e) {
        vSpan.setText(oldItem.getText(VSPAN_COL));
      }
      String[] insert =
          new String[] {
            String.valueOf(row),
            combo.getText(),
            widthText.getText(),
            heightText.getText(),
            hAlign.getText(),
            vAlign.getText(),
            hIndent.getText(),
            hSpan.getText(),
            vSpan.getText(),
            hGrab.getText(),
            vGrab.getText()
          };
      data.setElementAt(insert, row);
      for (int i = 0; i < TOTAL_COLS; i++) {
        oldItem.setText(i, ((String[]) (data.elementAt(row)))[i]);
      }
      if (!tab) {
        disposeEditors();
      }
    }
    setLayoutState();
    refreshLayoutComposite();
    setLayoutData();
    layoutComposite.layout(true);
    layoutGroup.layout(true);
  }
}
