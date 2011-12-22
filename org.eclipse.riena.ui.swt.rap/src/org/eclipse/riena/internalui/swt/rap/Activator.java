/*******************************************************************************
 * Copyright (c) 2007, 2011 compeople AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    compeople AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.riena.internalui.swt.rap;

import org.osgi.framework.BundleContext;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.rap.ui.internal.progress.JobManagerAdapter;
import org.eclipse.rwt.internal.lifecycle.FakeContextUtil;
import org.eclipse.rwt.internal.lifecycle.LifeCycleUtil;
import org.eclipse.rwt.internal.service.ContextProvider;
import org.eclipse.rwt.internal.service.ServiceContext;
import org.eclipse.rwt.lifecycle.UICallBack;
import org.eclipse.rwt.service.ISessionStore;
import org.eclipse.swt.internal.widgets.IDisplayAdapter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;

import org.eclipse.riena.ui.swt.AbstractRienaUIPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractRienaUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.riena.ui.swt.rap"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private UICallbackActivationListener uiCallbackListener;

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		removeRAPJobChangeListener();
		registerJobChangeListener();
	}

	@SuppressWarnings("restriction")
	private void removeRAPJobChangeListener() {
		Job.getJobManager().removeJobChangeListener(JobManagerAdapter.getInstance());
	}

	private void registerJobChangeListener() {
		uiCallbackListener = new UICallbackActivationListener();
		Job.getJobManager().addJobChangeListener(uiCallbackListener);
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		deregisterJobChangeListener();
		Activator.plugin = null;
		super.stop(context);
	}

	private void deregisterJobChangeListener() {
		Job.getJobManager().removeJobChangeListener(uiCallbackListener);
		uiCallbackListener = null;
	}

	/**
	 * Returns the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	@SuppressWarnings("restriction")
	private final static class UICallbackActivationListener extends JobChangeAdapter {
		private static final String SERVICE_CONTEXT = "serviceContext"; //$NON-NLS-1$
		private static final String RAP = "context"; //$NON-NLS-1$
		private static final String THREAD = "currentThread"; //$NON-NLS-1$

		@Override
		public void aboutToRun(final IJobChangeEvent event) {
			final ServiceContext context = (ServiceContext) event.getJob().getProperty(
					new QualifiedName(RAP, SERVICE_CONTEXT));

			if (context != null) {
				final Thread scheduler = (Thread) event.getJob().getProperty(new QualifiedName(RAP, THREAD));
				if (scheduler != null && scheduler != Thread.currentThread()) {
					ContextProvider.setContext(context);
				}
			}

			event.getJob().setProperty(new QualifiedName(RAP, SERVICE_CONTEXT), null);
			event.getJob().setProperty(new QualifiedName(RAP, THREAD), null);

			LifeCycleUtil.getSessionDisplay();
		}

		@Override
		public void scheduled(final IJobChangeEvent event) {
			final Display display = findDisplay(event.getJob());
			if (display != null && !display.isDisposed()) {
				final ServiceContext context = ContextProvider.getContext();
				if (context != null) {
					final Display sessionDisplay = LifeCycleUtil.getSessionDisplay();
					final IDisplayAdapter adapter = sessionDisplay.getAdapter(IDisplayAdapter.class);
					final ISessionStore session = adapter.getSessionStore();
					final ServiceContext fakeContext = FakeContextUtil.createFakeContext(session);
					event.getJob().setProperty(new QualifiedName(RAP, SERVICE_CONTEXT), fakeContext);
					event.getJob().setProperty(new QualifiedName(RAP, THREAD), Thread.currentThread());

				}
				display.asyncExec(new Runnable() {
					public void run() {
						final String id = String.valueOf(event.getJob().hashCode());
						UICallBack.activate(id);
					}
				});
			}
		}

		@Override
		public void done(final IJobChangeEvent event) {
			final Display display = LifeCycleUtil.getSessionDisplay();
			if (display != null && !display.isDisposed()) {
				try {
					display.asyncExec(new Runnable() {
						public void run() {
							final Job job = event.getJob();
							final String id = String.valueOf(job.hashCode());
							UICallBack.deactivate(id);
						}
					});
				} catch (final Throwable t) {

				}
				ContextProvider.releaseContextHolder();
			}
		}

		private static Display findDisplay(final Job job) {
			Display result = null;
			if (ContextProvider.hasContext()) {
				result = LifeCycleUtil.getSessionDisplay();
			} else {
				if (job instanceof UIJob) {
					final UIJob uiJob = (UIJob) job;
					result = uiJob.getDisplay();
					if (result == null) {
						final String msg = "UIJob " + uiJob.getName() //$NON-NLS-1$
								+ " cannot be scheduled without an associated display."; //$NON-NLS-1$
						throw new IllegalStateException(msg);
					}
				}
			}
			return result;
		}
	}

}
