/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.compare.internal.patch;

import java.util.*;

import org.eclipse.compare.internal.Utilities;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.swt.widgets.Shell;

/**
 * A Patcher 
 * - knows how to parse various patch file formats into some in-memory structure,
 * - holds onto the parsed data and the options to use when applying the patches,
 * - knows how to apply the patches to files and folders.
 */
public class WorkspacePatcher extends Patcher {

	private DiffProject[] fDiffProjects;
	private boolean fIsWorkspacePatch= false;
	private final Map retargetedDiffs = new HashMap();

	public WorkspacePatcher() {
		// nothing to do
	}

	public WorkspacePatcher(IResource target) {
		setTarget(target);
	}
	
	protected void patchParsed(PatchReader patchReader) {
		super.patchParsed(patchReader);
		fDiffProjects = patchReader.getDiffProjects();
		fIsWorkspacePatch = patchReader.isWorkspacePatch();
	}
	
	public DiffProject[] getDiffProjects() {
		return fDiffProjects;
	}

	boolean isWorkspacePatch() {
		return fIsWorkspacePatch;
	}

	//---- parsing patch files

	public void applyAll(IProgressMonitor pm, Shell shell, String title) throws CoreException {
		if (!fIsWorkspacePatch) {
			super.applyAll(pm, shell, title);
		} else {
			final int WORK_UNIT= 10;

			// get all files to be modified in order to call validateEdit
			List list= new ArrayList();
			for (int j= 0; j < fDiffProjects.length; j++) {
				DiffProject diffProject= fDiffProjects[j];
				if (diffProject.getProject().isAccessible())
					list.addAll(Arrays.asList(getTargetFiles(diffProject)));
			}
			// validate the files for editing
			if (!Utilities.validateResources(list, shell, title))
				return;

			FileDiff[] diffs = getDiffs();
			if (pm != null) {
				String message= PatchMessages.Patcher_Task_message;
				pm.beginTask(message, diffs.length * WORK_UNIT);
			}

			for (int i= 0; i < diffs.length; i++) {

				int workTicks= WORK_UNIT;

				FileDiff diff= diffs[i];
				if (isAccessible(diff)) {
					IFile file= getTargetFile(diff);
					IPath path= file.getProjectRelativePath();
					if (pm != null)
						pm.subTask(path.toString());
					createPath(file.getProject(), path);

					List failed= new ArrayList();
					List result= null;

					int type= diff.getDiffType(isReversed());
					switch (type) {
						case Differencer.ADDITION :
							// patch it and collect rejected hunks
							result= apply(diff, file, true, failed);
							store(createString(result), file, new SubProgressMonitor(pm, workTicks));
							workTicks -= WORK_UNIT;
							break;
						case Differencer.DELETION :
							file.delete(true, true, new SubProgressMonitor(pm, workTicks));
							workTicks -= WORK_UNIT;
							break;
						case Differencer.CHANGE :
							// patch it and collect rejected hunks
							result= apply(diff, file, false, failed);
							store(createString(result), file, new SubProgressMonitor(pm, workTicks));
							workTicks -= WORK_UNIT;
							break;
					}

					if (isGenerateRejectFile() && failed.size() > 0) {
						IPath pp= null;
						if (path.segmentCount() > 1) {
							pp= path.removeLastSegments(1);
							pp= pp.append(path.lastSegment() + REJECT_FILE_EXTENSION);
						} else
							pp= new Path(path.lastSegment() + REJECT_FILE_EXTENSION);
						file= createPath(file.getProject(), pp);
						if (file != null) {
							store(getRejected(failed), file, pm);
							try {
								IMarker marker= file.createMarker(MARKER_TYPE);
								marker.setAttribute(IMarker.MESSAGE, PatchMessages.Patcher_Marker_message);
								marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
							} catch (CoreException ex) {
								// NeedWork
							}
						}
					}
				}

				if (pm != null) {
					if (pm.isCanceled())
						break;
					if (workTicks > 0)
						pm.worked(workTicks);
				}
			}
		}
	}
	
	private boolean isAccessible(FileDiff diff) {
		return isEnabled(diff) && diff.getProject().getProject().isAccessible();
	}

	/**
	 * Returns the target files of all the Diffs contained by this 
	 * DiffProject.
	 * @param project 
	 * @return An array of IFiles that are targeted by the Diffs
	 */
	public IFile[] getTargetFiles(DiffProject project) {
		List files= new ArrayList();
		FileDiff[] diffs = project.getFileDiffs();
		for (int i = 0; i < diffs.length; i++) {
			FileDiff diff = diffs[i];
			if (isEnabled(diff)) {
				files.add(getTargetFile(diff));
			}
		}
		return (IFile[]) files.toArray(new IFile[files.size()]);
	}

	protected IFile getTargetFile(FileDiff diff) {
		IPath path = diff.getStrippedPath(getStripPrefixSegments(), isReversed());
		DiffProject project = getProject(diff);
		if (project != null)
			return project.getFile(path);
		return super.getTargetFile(diff);
	}
	
	private IPath getFullPath(FileDiff diff) {
		IPath path = diff.getStrippedPath(getStripPrefixSegments(), isReversed());
		DiffProject project = getProject(diff);
		if (project != null)
			return project.getFile(path).getFullPath();
		return getTarget().getFullPath().append(path);
	}

	public ISchedulingRule[] getTargetProjects() {
		List projects= new ArrayList();
		IResourceRuleFactory ruleFactory= ResourcesPlugin.getWorkspace().getRuleFactory();
		// Determine the appropriate scheduling rules 
		for (int i= 0; i < fDiffProjects.length; i++) {
			IProject tempProject= fDiffProjects[i].getProject();
			// The goal here is to lock as little of the workspace as necessary
			// but still allow the patcher to obtain the locks it needs.
			// As such, we need to get the modify rules from the rule factory for the .project file. A pessimistic
			// rule factory will return the root, while others might return just the project. Combining
			// this rule with the project will result in the smallest possible locking set.
			ISchedulingRule scheduleRule= ruleFactory.modifyRule(tempProject.getFile(IProjectDescription.DESCRIPTION_FILE_NAME));
			MultiRule multiRule= new MultiRule(new ISchedulingRule[] { scheduleRule, tempProject } );
			projects.add(multiRule);
		}
	
		return (ISchedulingRule[]) projects.toArray(new ISchedulingRule[projects.size()]);
	}

	public void setDiffProjects(DiffProject[] newProjectArray) {
		fDiffProjects = new DiffProject[newProjectArray.length];
		System.arraycopy(newProjectArray,0, fDiffProjects, 0, newProjectArray.length);
	}

	public void removeProject(DiffProject project) {
		DiffProject[] temp = new DiffProject[fDiffProjects.length - 1];
		int counter = 0;
		for (int i = 0; i < fDiffProjects.length; i++) {
			if (fDiffProjects[i] != project){
				temp[counter++] = fDiffProjects[i];
			}
		}
		fDiffProjects = temp;
	}	
	
	protected Object getElementParent(Object element) {
		if (element instanceof FileDiff && fDiffProjects != null) {
			FileDiff diff = (FileDiff) element;
			for (int i = 0; i < fDiffProjects.length; i++) {
				DiffProject project = fDiffProjects[i];
				if (project.contains(diff))
					return project;
			}
		}
		return null;
	}

	public boolean isRetargeted(Object object) {
		return retargetedDiffs.containsKey(object);
	}
	
	public IPath getOriginalPath(Object object) {
		return (IPath)retargetedDiffs.get(object);
	}

	public void retargetDiff(FileDiff diff, IFile file) {
		retargetedDiffs.put(diff, diff.getPath(false));
		Hunk[] hunks = diff.getHunks();
		
		if (isWorkspacePatch()){
			//since the diff has no more hunks to apply, remove it from the parent and the patcher
			diff.getProject().remove(diff);
		}
		removeDiff(diff);
		FileDiff newDiff = getDiffForFile(file);
		for (int i = 0; i < hunks.length; i++) {
			Hunk hunk = hunks[i];
			newDiff.add(hunk);
		}
	}

	private FileDiff getDiffForFile(IFile file) {
		DiffProject diffProject = null;
		FileDiff[] diffsToCheck;
		if (isWorkspacePatch()){
			// Check if the diff project already exists for the file
			IProject project = file.getProject();
			DiffProject[] diffProjects = getDiffProjects();
			for (int i = 0; i < diffProjects.length; i++) {
				if (diffProjects[i].getProject().equals(project)){
					diffProject = diffProjects[i];
					break;
				}
			}
			// If the project doesn't exist yet, create it and add it to the project list
			if (diffProject == null){
				diffProject = addDiffProjectForProject(project);
			}
			diffsToCheck = diffProject.getFileDiffs();
		} else {
			diffsToCheck = getDiffs();
		}
		// Check to see if a diff already exists for the file
		for (int i = 0; i < diffsToCheck.length; i++) {
			FileDiff fileDiff = diffsToCheck[i];
			if (isDiffForFile(fileDiff, file)) {
				return fileDiff;
			}
		}
		
		// Create a new diff for the file
		IPath path = getDiffPath(file);
		FileDiff newDiff = new FileDiff(path, 0, path, 0);
		if (diffProject != null){
			diffProject.add(newDiff);
		}
		addDiff(newDiff);
		return newDiff;
	}

	private IPath getDiffPath(IFile file) {
		DiffProject project = getDiffProject(file.getProject());
		if (project != null) {
			return file.getProjectRelativePath();
		}
		return file.getFullPath().removeFirstSegments(getTarget().getFullPath().segmentCount());
	}

	private boolean isDiffForFile(FileDiff fileDiff, IFile file) {
		return getFullPath(fileDiff).equals(file.getFullPath());
	}

	private DiffProject addDiffProjectForProject(IProject project) {
		DiffProject[] diffProjects = getDiffProjects();
		DiffProject diffProject = new DiffProject(project);
		DiffProject[] newProjectArray = new DiffProject[diffProjects.length + 1];
		System.arraycopy(diffProjects, 0, newProjectArray, 0, diffProjects.length);
		newProjectArray[diffProjects.length] = diffProject;
		setDiffProjects(newProjectArray);
		return diffProject;
	}

	public void retargetHunk(Hunk hunk, IFile file) {
		FileDiff newDiff = getDiffForFile(file);
		newDiff.add(hunk);
	}

	public void retargetProject(DiffProject project, IProject targetProject) {
		retargetedDiffs.put(project, project.getProject().getFullPath());
		FileDiff[] diffs = project.getFileDiffs();
		DiffProject selectedProject = getDiffProject(targetProject);
		if (selectedProject == null)
			selectedProject = addDiffProjectForProject(targetProject);
		// Copy over the diffs to the new project
		for (int i = 0; i < diffs.length; i++) {
			selectedProject.add(diffs[i]);
		}
		// Since the project has been retargeted, remove it from the patcher
		removeProject(project);
	}
	
	/**
	 * Return the diff project for the given project
	 * or <code>null</code> if the diff project doesn't exist
	 * or if the patch is not a workspace patch.
	 * @param project the project
	 * @return the diff project for the given project
	 * or <code>null</code>
	 */
	private DiffProject getDiffProject(IProject project) {
		if (!isWorkspacePatch())
			return null;
		DiffProject[] projects = getDiffProjects();
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].getProject().equals(project))
				return projects[i];
		}
		return null;
	}
	
	int getStripPrefixSegments() {
		// Segments are never stripped from a workspace patch
		if (isWorkspacePatch())
			return 0;
		return super.getStripPrefixSegments();
	}
    
}
