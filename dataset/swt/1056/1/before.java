class PlaceHold {
  void handleEvent(Event event) {
    if (ignoreEvents) {
      return;
    }
    if (event.widget instanceof Combo) {
      Combo combo = ((Combo) (event.widget));
      int prevSelectIndex = ((Integer) (combo.getData())).intValue();
      String text = combo.getText();
      int newSelectIndex = combo.indexOf(text);
      if ((prevSelectIndex != newSelectIndex) || (newSelectIndex == (-1))) {
        ignoreEvents = true;
        combo.setData(new Integer(newSelectIndex));
        if (combo == charSetCombo) {
          initFaceNameCombo();
        } else if (combo == faceNameCombo) {
          initExtStyleCombo();
        } else if (combo == extStyleCombo) {
          initSizeCombo();
        } else if (combo == fontSizeCombo) {
          initStyleCombo();
        }
        updateSample();
        if (newSelectIndex != (-1)) {
          combo.select(newSelectIndex);
        }
        ignoreEvents = false;
      }
    } else if (event.widget == okButton) {
      okSelected = true;
      shell.close();
    } else if (event.widget == cancelButton) {
      okSelected = false;
      shell.close();
    }
  }
}
