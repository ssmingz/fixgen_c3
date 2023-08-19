class PlaceHold {
  protected synchronized org.eclipse.swt.widgets.Control createDialogArea(
      org.eclipse.swt.widgets.Composite parent2) {
    org.eclipse.swt.widgets.Composite parent =
        ((org.eclipse.swt.widgets.Composite) (super.createDialogArea(parent2)));
    getShell().setText(Utilities.getString(fBundle, "title")); // $NON-NLS-1$

    Splitter vsplitter = new Splitter(parent, org.eclipse.swt.SWT.VERTICAL);
    vsplitter.setLayoutData(
        new org.eclipse.swt.layout.GridData(
            ((org.eclipse.swt.layout.GridData.HORIZONTAL_ALIGN_FILL
                        | org.eclipse.swt.layout.GridData.GRAB_HORIZONTAL)
                    | org.eclipse.swt.layout.GridData.VERTICAL_ALIGN_FILL)
                | org.eclipse.swt.layout.GridData.GRAB_VERTICAL));
    vsplitter.addDisposeListener(
        new DisposeListener() {
          public void widgetDisposed(DisposeEvent e) {
            if (fCompareConfiguration != null) {
              fCompareConfiguration.dispose();
              fCompareConfiguration = null;
            }
            if (fDateImage != null) {
              fDateImage.dispose();
              fDateImage = null;
            }
            if (fTimeImage != null) {
              fTimeImage.dispose();
              fTimeImage = null;
            }
          }
        });
    if (fAddMode) {
      // we need two panes: the left for the elements, the right one for the editions
      Splitter hsplitter = new Splitter(vsplitter, org.eclipse.swt.SWT.HORIZONTAL);
      fMemberPane =
          new CompareViewerPane(hsplitter, org.eclipse.swt.SWT.BORDER | org.eclipse.swt.SWT.FLAT);
      fMemberPane.setText(Utilities.getString(fBundle, "memberPaneTitle")); // $NON-NLS-1$

      int flags = org.eclipse.swt.SWT.H_SCROLL | org.eclipse.swt.SWT.V_SCROLL;
      if (fMultiSelect) flags |= org.eclipse.swt.SWT.CHECK;

      fMemberTable = new org.eclipse.swt.widgets.Table(fMemberPane, flags);
      fMemberTable.addSelectionListener(
          new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
              if (e.detail == org.eclipse.swt.SWT.CHECK) {
                if (e.item instanceof org.eclipse.swt.widgets.TableItem) {
                  org.eclipse.swt.widgets.TableItem ti =
                      ((org.eclipse.swt.widgets.TableItem) (e.item));
                  Object data = ti.getData();
                  if (ti.getChecked()) fArrayList.add(data);
                  else fArrayList.remove(data);

                  if (fCommitButton != null) fCommitButton.setEnabled(fArrayList.size() > 0);

                  fMemberTable.setSelection(new org.eclipse.swt.widgets.TableItem[] {ti});
                }
              }
              handleMemberSelect(e.item);
            }
          });
      fMemberPane.setContent(fMemberTable);
      fMemberTable.setFocus();
      fEditionPane =
          new CompareViewerPane(hsplitter, org.eclipse.swt.SWT.BORDER | org.eclipse.swt.SWT.FLAT);
    } else {
      if (fStructureCompare) {
        // we need two panes: the left for the elements, the right one for the structured diff
        Splitter hsplitter = new Splitter(vsplitter, org.eclipse.swt.SWT.HORIZONTAL);
        fEditionPane =
            new CompareViewerPane(hsplitter, org.eclipse.swt.SWT.BORDER | org.eclipse.swt.SWT.FLAT);
        fStructuredComparePane =
            new CompareViewerSwitchingPane(
                hsplitter, org.eclipse.swt.SWT.BORDER | org.eclipse.swt.SWT.FLAT, true) {
              protected org.eclipse.compare.Viewer getViewer(Viewer oldViewer, Object input) {
                if (input instanceof ICompareInput)
                  return CompareUI.findStructureViewer(
                      oldViewer, ((ICompareInput) (input)), this, getCompareConfiguration());

                return null;
              }
            };
        fStructuredComparePane.addSelectionChangedListener(
            new ISelectionChangedListener() {
              public void selectionChanged(SelectionChangedEvent e) {
                feedInput2(e.getSelection());
              }
            });
      } else {
        // only a single pane showing the editions
        fEditionPane =
            new CompareViewerPane(vsplitter, org.eclipse.swt.SWT.BORDER | org.eclipse.swt.SWT.FLAT);
      }
      if (fTitleArg == null) fTitleArg = fTargetPair.getItem().getName();

      String titleFormat = Utilities.getString(fBundle, "treeTitleFormat"); // $NON-NLS-1$

      String title = com.ibm.icu.text.MessageFormat.format(titleFormat, new String[] {fTitleArg});
      fEditionPane.setText(title);
      if (fTitleImage != null) fEditionPane.setImage(fTitleImage);
    }
    fEditionTree =
        new org.eclipse.swt.widgets.Tree(
            fEditionPane, org.eclipse.swt.SWT.H_SCROLL | org.eclipse.swt.SWT.V_SCROLL);
    fEditionTree.addSelectionListener(
        new SelectionAdapter() {
          // public void widgetDefaultSelected(SelectionEvent e) {
          // handleDefaultSelected();
          // }
          public void widgetSelected(SelectionEvent e) {
            feedInput(e.item);
          }
        });
    fEditionPane.setContent(fEditionTree);
    // now start the thread (and forget about it)
    if (fThread != null) {
      fThread.start();
      fThread = null;
    }
    fContentPane =
        new CompareViewerSwitchingPane(
            vsplitter, org.eclipse.swt.SWT.BORDER | org.eclipse.swt.SWT.FLAT) {
          protected org.eclipse.compare.Viewer getViewer(Viewer oldViewer, Object input) {
            return CompareUI.findContentViewer(oldViewer, input, this, getCompareConfiguration());
          }
        };
    vsplitter.setWeights(new int[] {30, 70});
    applyDialogFont(parent);
    return parent;
  }
}
